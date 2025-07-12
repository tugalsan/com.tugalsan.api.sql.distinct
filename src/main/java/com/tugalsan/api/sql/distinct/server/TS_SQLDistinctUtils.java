package com.tugalsan.api.sql.distinct.server;

import com.tugalsan.api.sql.conn.server.TS_SQLConnAnchor;

public class TS_SQLDistinctUtils {

    private TS_SQLDistinctUtils() {

    }

    public static TS_SQLDistinct distict(TS_SQLConnAnchor anchor, CharSequence tableName) {
        return new TS_SQLDistinct(anchor, tableName);
    }

    public static String columnEmpty() {
        return "''";
    }

//    public static void test() {
//        TS_SQLDistinctUtils.select(null, "tn").columns(columnNames -> {
//            columnNames.add("ali gel");
//        }).whereConditionAnd(conditions -> {
//            conditions.lngEq("", 0);
//        }).groupNone().orderNone().rowIdxOffsetNone().rowSizeLimitNone().walk(null, rs -> {
//        });
//    }
}
