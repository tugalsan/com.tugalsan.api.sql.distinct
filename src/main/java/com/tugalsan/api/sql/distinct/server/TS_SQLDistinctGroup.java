package com.tugalsan.api.sql.distinct.server;


import com.tugalsan.api.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_In1;
import com.tugalsan.api.sql.conn.server.TS_SQLConnColUtils;
import com.tugalsan.api.sql.group.server.TS_SQLGroupUtils;
import java.util.Arrays;
import java.util.List;

public class TS_SQLDistinctGroup {

    public TS_SQLDistinctGroup(TS_SQLDistinctExecutor executor) {
        this.executor = executor;
    }
    private final TS_SQLDistinctExecutor executor;

    public TS_SQLDistinctOrder group(int colIdx) {
        var colNames = TS_SQLConnColUtils.names(executor.anchor, executor.tableName);
        return group(colNames.get(colIdx));
    }

    public TS_SQLDistinctOrder group(String columnName) {
        return group(columnNames -> {
            columnNames.add(columnName);
        });
    }

    public TS_SQLDistinctOrder group(TGS_FuncMTU_In1<List<String>> columnNames) {
        executor.group = TS_SQLGroupUtils.group();
        columnNames.run(executor.group.columnNames);
        return new TS_SQLDistinctOrder(executor);
    }

    public TS_SQLDistinctOrder groupNone() {
        return new TS_SQLDistinctOrder(executor);
    }

    public TS_SQLDistinctOrder group(String[] columns) {
        if (columns == null || columns.length == 0) {
            return groupNone();
        }
        executor.group = TS_SQLGroupUtils.group();
        Arrays.stream(columns).forEachOrdered(cn -> executor.group.columnNames.add(cn));
        return new TS_SQLDistinctOrder(executor);
    }

    public TS_SQLDistinctOrder group(CharSequence... columns) {
        if (columns == null || columns.length == 0) {
            return groupNone();
        }
        executor.group = TS_SQLGroupUtils.group();
        Arrays.stream(columns).forEachOrdered(cn -> executor.group.columnNames.add(cn.toString()));
        return new TS_SQLDistinctOrder(executor);
    }
}
