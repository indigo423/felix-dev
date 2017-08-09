/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.felix.converter.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

import org.osgi.util.converter.ConverterBuilder;
import org.osgi.util.converter.Functioning;
import org.osgi.util.converter.Rule;
import org.osgi.util.function.Function;

public class ConverterImpl implements InternalConverter {
    @Override
    public InternalConverting convert(Object obj) {
        return new ConvertingImpl(this, obj);
    }

    @Override
    public Functioning function() {
        return new FunctioningImpl(this);
    }

    public void addStandardRules(ConverterBuilder cb) {
//        cb.rule(new Rule<Calendar, String>(f -> f.getTime().toInstant().toString()) {});
        cb.rule(new Rule<Calendar, String>(new Function<Calendar, String>() {
            @Override
            public String apply(Calendar f) {
                return f.getTime()./* toInstant(). */toString();
            }
        }) {});

//        cb.rule(new Rule<String, Calendar>(f -> {
//            Calendar cc = Calendar.getInstance();
//            cc.setTime(Date.from(Instant.parse(f)));
//            return cc;
//        }) {});
        cb.rule(new Rule<String, Calendar>(new Function<String, Calendar>() {
            @Override
            public Calendar apply(String f) {
                Calendar cc = Calendar.getInstance();
                //cc.setTime(Date.from(Instant.parse(f)));
                return cc;
            }
        }) {});

//        cb.rule(new Rule<Calendar,Long>(f -> f.getTime().getTime()) {});
        cb.rule(new Rule<Calendar, Long>(new Function<Calendar, Long>() {
            @Override
            public Long apply(Calendar f) {
                return f.getTime().getTime();
            }
        }) {});

//        cb.rule(new Rule<Long,Calendar>(f -> new Calendar.Builder().setInstant(f).build()) {});
        cb.rule(new Rule<Long, Calendar>(new Function<Long, Calendar>() {
            @Override
            public Calendar apply(Long f) {
//                new Calendar.Builder().setInstant(f).build()
                return null;
            }
        }) {});

//        cb.rule(new Rule<Character,Boolean>(c -> c.charValue() != 0) {});
        cb.rule(new Rule<Character, Boolean>(new Function<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return c.charValue() != 0;
            }
        }) {});

//        cb.rule(new Rule<Boolean,Character>(b -> b.booleanValue() ? (char) 1 : (char) 0) {});
        cb.rule(new Rule<Boolean, Character>(new Function<Boolean, Character>() {
            @Override
            public Character apply(Boolean b) {
                return b.booleanValue() ? (char) 1: (char) 0;
            }
        }) {});

//        cb.rule(new Rule<Character,Integer>(c -> (int) c.charValue()) {});
        cb.rule(new Rule<Character, Integer>(new Function<Character, Integer>() {
            @Override
            public Integer apply(Character c) {
                return (int) c.charValue();
            }
        }) {});

//        cb.rule(new Rule<Character,Long>(c -> (long) c.charValue()) {});
        cb.rule(new Rule<Character, Long>(new Function<Character, Long>() {
            @Override
            public Long apply(Character c) {
                return (long) c.charValue();
            }
        }) {});

//        cb.rule(new Rule<String,Character>(f -> f.length() > 0 ? f.charAt(0) : 0) {});
        cb.rule(new Rule<String, Character>(new Function<String, Character>() {
            @Override
            public Character apply(String f) {
                return f.length() > 0 ? f.charAt(0) : 0;
            }
        }) {});

//        cb.rule(new Rule<String,Class<?>>(this::loadClassUnchecked) {});
        cb.rule(new Rule<String, Class<?>>(new Function<String, Class<?>>() {
            @Override
            public Class<?> apply(String cn) {
                return loadClassUnchecked(cn);
            }
        }) {});

//        cb.rule(new Rule<Date,Long>(Date::getTime) {});
        cb.rule(new Rule<Date, Long>(new Function<Date, Long>() {
            @Override
            public Long apply(Date d) {
                return d.getTime();
            }
        }) {});
//        cb.rule(new Rule<Long,Date>(f -> new Date(f)) {});
        cb.rule(new Rule<Long, Date>(new Function<Long, Date>() {
            @Override
            public Date apply(Long f) {
                return new Date(f);
            }
        }) {});

//        cb.rule(new Rule<Date,String>(f -> f.toInstant().toString()) {});
        cb.rule(new Rule<Date, String>(new Function<Date, String>() {
            @Override
            public String apply(Date f) {
                return null; // f.toInstant().toString()
            }
        }) {});

//        cb.rule(new Rule<String,Date>(f -> Date.from(Instant.parse(f))) {});
        cb.rule(new Rule<String, Date>(new Function<String, Date>() {
            @Override
            public Date apply(String f) {
                return null; // Date.from(Instant.parse(f))
            }
        }) {});

        // TODO
//        cb.rule(new Rule<String, LocalDateTime>(LocalDateTime::parse) {});
//        cb.rule(new Rule<String, LocalDate>(LocalDate::parse) {});
//        cb.rule(new Rule<String, LocalTime>(LocalTime::parse) {});
//        cb.rule(new Rule<String, OffsetDateTime>(OffsetDateTime::parse) {});
//        cb.rule(new Rule<String, OffsetTime>(OffsetTime::parse) {});
//        cb.rule(new Rule<String, ZonedDateTime>(ZonedDateTime::parse) {});

//        cb.rule(new Rule<String, Pattern>(Pattern::compile) {});
        cb.rule(new Rule<String, Pattern>(new Function<String, Pattern>() {
            @Override
            public Pattern apply(String ps) {
                return Pattern.compile(ps);
            }
        }) {});

//        cb.rule(new Rule<String, UUID>(UUID::fromString) {});
        cb.rule(new Rule<String, UUID>(new Function<String, UUID>() {
            @Override
            public UUID apply(String uuid) {
                return UUID.fromString(uuid);
            }
        }) {});

        // Special conversions between character arrays and String
//        cb.rule(new Rule<char[], String>(this::charArrayToString) {});
        cb.rule(new Rule<char[], String>(new Function<char[], String>() {
            @Override
            public String apply(char[] ca) {
                return charArrayToString(ca);
            }
        }) {});

//        cb.rule(new Rule<Character[], String>(this::characterArrayToString) {});
        cb.rule(new Rule<Character[], String>(new Function<Character[], String>() {
            @Override
            public String apply(Character[] ca) {
                return characterArrayToString(ca);
            }
        }) {});

//        cb.rule(new Rule<String, char[]>(this::stringToCharArray) {});
        cb.rule(new Rule<String, char[]>(new Function<String, char[]>() {
            @Override
            public char[] apply(String s) {
                return stringToCharArray(s);
            }
        }) {});
//        cb.rule(new Rule<String, Character[]>(this::stringToCharacterArray) {});
        cb.rule(new Rule<String, Character[]>(new Function<String, Character[]>() {
            @Override
            public Character[] apply(String s) {
                return stringToCharacterArray(s);
            }
        }) {});
    }

    private String charArrayToString(char[] ca) {
        StringBuilder sb = new StringBuilder(ca.length);
        for (char c : ca) {
            sb.append(c);
        }
        return sb.toString();
    }

    private String characterArrayToString(Character[] ca) {
        return charArrayToString(convert(ca).to(char[].class));
    }

    private char[] stringToCharArray(String s) {
        char[] ca = new char[s.length()];

        for (int i=0; i<s.length(); i++) {
            ca[i] = s.charAt(i);
        }
        return ca;
    }

    private Character[] stringToCharacterArray(String s) {
        return convert(stringToCharArray(s)).to(Character[].class);
    }

    private Class<?> loadClassUnchecked(String className) {
        try {
            return getClass().getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(className);
        }
    }

    @Override
    public ConverterBuilderImpl newConverterBuilder() {
        return new ConverterBuilderImpl(this);
    }
}
