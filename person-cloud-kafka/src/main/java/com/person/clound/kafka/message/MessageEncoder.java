package com.person.clound.kafka.message;

interface MessageEncoder<T> {
	byte[] encode(T msg);
}
