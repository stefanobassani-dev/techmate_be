package org.techmate.techmate_be.configuration;

import com.p6spy.engine.common.P6Util;
import com.p6spy.engine.spy.P6SpyOptions;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomTimeLogFormatter implements MessageFormattingStrategy {

    private static final MessageFormattingStrategy FALLBACK_FORMATTING_STRATEGY = new com.p6spy.engine.spy.appender.SingleLineFormat();

    private static final DateTimeFormatter CUSTOM_FORMATTER =
            DateTimeFormatter.ofPattern("HH:mm:ss.SSSSSS dd-MM-yyyy")
                    .withZone(ZoneId.systemDefault());

    public static final String CONNECTION_ID = "%(connectionId)";
    public static final String CURRENT_TIME = "%(currentTime)";
    public static final String EXECUTION_TIME = "%(executionTime)";
    public static final String CATEGORY = "%(category)";
    public static final String EFFECTIVE_SQL = "%(effectiveSql)";
    public static final String EFFECTIVE_SQL_SINGLELINE = "%(effectiveSqlSingleLine)";
    public static final String SQL = "%(sql)";
    public static final String SQL_SINGLE_LINE = "%(sqlSingleLine)";
    public static final String URL = "%(url)";

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category,
                                String prepared, String sql, String url) {

        String customFormat = P6SpyOptions.getActiveInstance().getCustomLogMessageFormat();
        if (customFormat == null) {
            return FALLBACK_FORMATTING_STRATEGY.formatMessage(connectionId, now, elapsed, category, prepared, sql, url);
        }


        String currentTime = CUSTOM_FORMATTER.format(Instant.now());
        String executionTime = elapsed + " ms";

        return customFormat
                .replaceAll(Pattern.quote(CONNECTION_ID), Integer.toString(connectionId))
                .replaceAll(Pattern.quote(CURRENT_TIME), currentTime)
                .replaceAll(Pattern.quote(EXECUTION_TIME), executionTime)
                .replaceAll(Pattern.quote(CATEGORY), category)
                .replaceAll(Pattern.quote(EFFECTIVE_SQL), Matcher.quoteReplacement(prepared))
                .replaceAll(Pattern.quote(EFFECTIVE_SQL_SINGLELINE), Matcher.quoteReplacement(P6Util.singleLine(prepared)))
                .replaceAll(Pattern.quote(SQL), Matcher.quoteReplacement(sql))
                .replaceAll(Pattern.quote(SQL_SINGLE_LINE), Matcher.quoteReplacement(P6Util.singleLine(sql)))
                .replaceAll(Pattern.quote(URL), url);
    }
}
