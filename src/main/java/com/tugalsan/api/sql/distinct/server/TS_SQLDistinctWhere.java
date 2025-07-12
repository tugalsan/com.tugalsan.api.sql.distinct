package com.tugalsan.api.sql.distinct.server;


import com.tugalsan.api.function.client.maythrowexceptions.unchecked.TGS_FuncMTU_In1;
import com.tugalsan.api.sql.conn.server.TS_SQLConnColUtils;
import com.tugalsan.api.sql.where.server.TS_SQLWhereConditions;
import com.tugalsan.api.sql.where.server.TS_SQLWhereGroups;
import com.tugalsan.api.sql.where.server.TS_SQLWhereUtils;

public class TS_SQLDistinctWhere {

    public TS_SQLDistinctWhere(TS_SQLDistinctExecutor executor) {
        this.executor = executor;
    }
    final private TS_SQLDistinctExecutor executor;

    public TS_SQLDistinctGroup whereGroupAnd(TGS_FuncMTU_In1<TS_SQLWhereGroups> gAnd) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsAnd(gAnd);
        return new TS_SQLDistinctGroup(executor);
    }

    public TS_SQLDistinctGroup whereGroupOr(TGS_FuncMTU_In1<TS_SQLWhereGroups> gOr) {
        executor.where = TS_SQLWhereUtils.where();
        executor.where.groupsOr(gOr);
        return new TS_SQLDistinctGroup(executor);
    }

    public TS_SQLDistinctGroup whereConditionAnd(TGS_FuncMTU_In1<TS_SQLWhereConditions> cAnd) {
        whereGroupAnd(where -> where.conditionsAnd(cAnd));
        return new TS_SQLDistinctGroup(executor);
    }

    public TS_SQLDistinctGroup whereConditionOr(TGS_FuncMTU_In1<TS_SQLWhereConditions> cOr) {
        whereGroupOr(where -> where.conditionsOr(cOr));
        return new TS_SQLDistinctGroup(executor);
    }

    public TS_SQLDistinctExecutor whereFirstColumnAsId(long id) {
        return whereConditionAnd(conditions -> {
            conditions.lngEq(
                    TS_SQLConnColUtils.names(executor.anchor, executor.tableName).get(0),
                    id
            );
        }).groupNone().orderNone().rowIdxOffsetNone().rowSizeLimitNone();
    }

    public TS_SQLDistinctGroup whereConditionNone() {
        return new TS_SQLDistinctGroup(executor);
    }
}
