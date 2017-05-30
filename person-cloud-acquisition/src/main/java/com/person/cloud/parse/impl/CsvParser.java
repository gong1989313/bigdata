package com.person.cloud.parse.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.person.cloud.common.queue.ServiceQueue;
import com.person.cloud.enums.StatusTypeEnum;
import com.person.cloud.parse.Parser;
import com.person.cloud.utils.ConfigInfo;

public class CsvParser implements Parser {
	private static transient Log logger = LogFactory.getLog(CsvParser.class);
	private Queue<List<String>> csvTxtDataQueue = ServiceQueue.getInstance().getCsvTxtDataQueue();

	@Override
	public void parse(int SIZE, String file) {
		logger.info("ready to parse csvFile:" + file);
		BufferedReader reader = null;
		int ch = 0, count = 0, index = 0, delimiter = ConfigInfo.delimiter;
		List<String> paramList = new ArrayList<String>(SIZE);
		StringBuilder tempSb = new StringBuilder();
		StringBuilder quotesBackup = new StringBuilder();
		StatusTypeEnum status = StatusTypeEnum.NewFieldStart;
		boolean isQutesFlag = false;
		try {
			reader = this.getBufReader(file);
			while ((ch = reader.read()) != -1) {
				if (ch == 65279) {
					continue;
				} else if (ch == delimiter) { // ','=(44)
					if (status == StatusTypeEnum.QuotesField) {
						status = StatusTypeEnum.QuotesField;
					} else if (status == StatusTypeEnum.NewFieldStart) {
						// new field
						paramList.add(tempSb.toString());
						this.clearSB(tempSb);
						count++;
						status = StatusTypeEnum.NewFieldStart;
					} else if (status == StatusTypeEnum.NonQuotesField) {
						// non quotes
						tempSb.append(quotesBackup);
						paramList.add(tempSb.toString());
						this.clearSB(tempSb);
						this.clearSB(quotesBackup);
						count++;
						status = StatusTypeEnum.NewFieldStart;
					} else if (status == StatusTypeEnum.QuoteInQuotesField) {
						tempSb.append(quotesBackup);
						paramList.add(tempSb.toString());
						this.clearSB(tempSb);
						this.clearSB(quotesBackup);
						count++;
						status = StatusTypeEnum.NewFieldStart;
					}
				} else if (ch == 34 || ch == 39) { // '\"'=(34) '\''=(39)
					if (status == StatusTypeEnum.NewFieldStart) {
						status = StatusTypeEnum.QuotesField;
						isQutesFlag = true; // mark ch is quotes character
					} else if (status == StatusTypeEnum.QuotesField) {
						quotesBackup.append((char) ch);
						status = StatusTypeEnum.NonQuotesField;
						isQutesFlag = false; // mark ch is not quotes character
					} else if (status == StatusTypeEnum.NonQuotesField) {
						quotesBackup.append((char) ch);
						status = StatusTypeEnum.QuoteInQuotesField;
					} else if (status == StatusTypeEnum.QuoteInQuotesField) {
						quotesBackup.append((char) ch);
						status = StatusTypeEnum.QuoteInQuotesField;
					}
				} else if (ch == 13 || ch == 10) { // '\r'=(13) '\n'=(10)
					if (status == StatusTypeEnum.NewFieldStart) {
						if (count < SIZE) {
							continue;
						} else {
							if (ch == 13) {
								count++;
							} else if (ch == 10) {
								count = 0;
							}
						}
					} else if (status == StatusTypeEnum.QuotesField) {
						quotesBackup.append((char) ch);
						if (isQutesFlag && count < SIZE) {
							this.processQuotesString(SIZE, tempSb, quotesBackup, count, paramList);
							count = 0;
							this.clearSB(tempSb);
							this.clearSB(quotesBackup);
							status = StatusTypeEnum.RowSeparator;
						}
					}
					// end line
					if (count >= SIZE) {
						paramList.add(tempSb.toString());
						this.clearSB(tempSb);
						this.clearSB(quotesBackup);
						count = 0;
						status = StatusTypeEnum.RowSeparator;
					}
				} else if (status == StatusTypeEnum.NewFieldStart) {
					// '\r'=(13) '\n'=(10)
					if (ch == 13 || ch == 10) {
						continue;
					}
					tempSb.append((char) ch);
				}
				if (status == StatusTypeEnum.QuotesField) {
					quotesBackup.append((char) ch);
				} else if (status == StatusTypeEnum.NonQuotesField) {
					tempSb.append(quotesBackup);
					this.clearSB(quotesBackup);
				}
				if (status == StatusTypeEnum.RowSeparator) {
					status = StatusTypeEnum.NewFieldStart;
					this.putTxtData2Queue(++index, paramList);
					paramList.clear();
				}
			}
			if (count >= SIZE - 1) {
				paramList.add(tempSb.toString());
				quotesBackup = tempSb = null;
				this.putTxtData2Queue(++index, paramList);
				paramList = null;
			} else if (isQutesFlag && count < SIZE) {
				this.processQuotesString(SIZE, tempSb, quotesBackup, count, paramList);
				quotesBackup = tempSb = null;
				count = 0;
			}
		} catch (IOException e) {
			logger.error("parse csvFile:" + file + " failed.", e);
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
					logger.info("parse csvFile:" + file + " finish, close reader...");
				} catch (IOException e) {
					logger.error("close csvFile:" + file + " reader failed.", e);
					e.printStackTrace();
				}
			}
		}
	}

	private BufferedReader getBufReader(String file) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(
					new InputStreamReader(new BufferedInputStream(new FileInputStream(file)), "utf-8"),
					5 * 1024 * 1024);
		} catch (UnsupportedEncodingException e) {
			logger.error("open csvFile:" + file + " failed.", e);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			logger.error("open csvFile:" + file + " failed.", e);
			e.printStackTrace();
		}
		return reader;
	}

	private void clearSB(StringBuilder sb) {
		if (sb == null) {
			return;
		}
		sb.delete(0, sb.length());
	}

	private void processQuotesString(int SIZE, StringBuilder tempSb, StringBuilder quotesBackup, int count,
			List<String> paramList) {
		if (quotesBackup == null) {
			return;
		}
		String quotesStr = StringUtils.replace(tempSb.append(quotesBackup).toString(), "\r", "");
		String[] strs = quotesStr.split(String.valueOf(ConfigInfo.delimiter));
		int len = strs.length;
		for (int i = 0; i < len; i++) {
			paramList.add(count + i, strs[i]);
		}
	}

	private void putTxtData2Queue(int index, List<String> paramList) {
		logger.debug("parse "+index+" --> "+paramList);
		csvTxtDataQueue.add(paramList);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
