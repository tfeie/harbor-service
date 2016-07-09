package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HyUserAssetsCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyUserAssetsCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andAssetsIdIsNull() {
            addCriterion("ASSETS_ID is null");
            return (Criteria) this;
        }

        public Criteria andAssetsIdIsNotNull() {
            addCriterion("ASSETS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAssetsIdEqualTo(String value) {
            addCriterion("ASSETS_ID =", value, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdNotEqualTo(String value) {
            addCriterion("ASSETS_ID <>", value, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdGreaterThan(String value) {
            addCriterion("ASSETS_ID >", value, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdGreaterThanOrEqualTo(String value) {
            addCriterion("ASSETS_ID >=", value, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdLessThan(String value) {
            addCriterion("ASSETS_ID <", value, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdLessThanOrEqualTo(String value) {
            addCriterion("ASSETS_ID <=", value, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdLike(String value) {
            addCriterion("ASSETS_ID like", value, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdNotLike(String value) {
            addCriterion("ASSETS_ID not like", value, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdIn(List<String> values) {
            addCriterion("ASSETS_ID in", values, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdNotIn(List<String> values) {
            addCriterion("ASSETS_ID not in", values, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdBetween(String value1, String value2) {
            addCriterion("ASSETS_ID between", value1, value2, "assetsId");
            return (Criteria) this;
        }

        public Criteria andAssetsIdNotBetween(String value1, String value2) {
            addCriterion("ASSETS_ID not between", value1, value2, "assetsId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andAssetsTypeIsNull() {
            addCriterion("ASSETS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andAssetsTypeIsNotNull() {
            addCriterion("ASSETS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andAssetsTypeEqualTo(String value) {
            addCriterion("ASSETS_TYPE =", value, "assetsType");
            return (Criteria) this;
        }

        public Criteria andAssetsTypeNotEqualTo(String value) {
            addCriterion("ASSETS_TYPE <>", value, "assetsType");
            return (Criteria) this;
        }

        public Criteria andAssetsTypeGreaterThan(String value) {
            addCriterion("ASSETS_TYPE >", value, "assetsType");
            return (Criteria) this;
        }

        public Criteria andAssetsTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ASSETS_TYPE >=", value, "assetsType");
            return (Criteria) this;
        }

        public Criteria andAssetsTypeLessThan(String value) {
            addCriterion("ASSETS_TYPE <", value, "assetsType");
            return (Criteria) this;
        }

        public Criteria andAssetsTypeLessThanOrEqualTo(String value) {
            addCriterion("ASSETS_TYPE <=", value, "assetsType");
            return (Criteria) this;
        }

        public Criteria andAssetsTypeLike(String value) {
            addCriterion("ASSETS_TYPE like", value, "assetsType");
            return (Criteria) this;
        }

        public Criteria andAssetsTypeNotLike(String value) {
            addCriterion("ASSETS_TYPE not like", value, "assetsType");
            return (Criteria) this;
        }

        public Criteria andAssetsTypeIn(List<String> values) {
            addCriterion("ASSETS_TYPE in", values, "assetsType");
            return (Criteria) this;
        }

        public Criteria andAssetsTypeNotIn(List<String> values) {
            addCriterion("ASSETS_TYPE not in", values, "assetsType");
            return (Criteria) this;
        }

        public Criteria andAssetsTypeBetween(String value1, String value2) {
            addCriterion("ASSETS_TYPE between", value1, value2, "assetsType");
            return (Criteria) this;
        }

        public Criteria andAssetsTypeNotBetween(String value1, String value2) {
            addCriterion("ASSETS_TYPE not between", value1, value2, "assetsType");
            return (Criteria) this;
        }

        public Criteria andAssetsUnitIsNull() {
            addCriterion("ASSETS_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andAssetsUnitIsNotNull() {
            addCriterion("ASSETS_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andAssetsUnitEqualTo(String value) {
            addCriterion("ASSETS_UNIT =", value, "assetsUnit");
            return (Criteria) this;
        }

        public Criteria andAssetsUnitNotEqualTo(String value) {
            addCriterion("ASSETS_UNIT <>", value, "assetsUnit");
            return (Criteria) this;
        }

        public Criteria andAssetsUnitGreaterThan(String value) {
            addCriterion("ASSETS_UNIT >", value, "assetsUnit");
            return (Criteria) this;
        }

        public Criteria andAssetsUnitGreaterThanOrEqualTo(String value) {
            addCriterion("ASSETS_UNIT >=", value, "assetsUnit");
            return (Criteria) this;
        }

        public Criteria andAssetsUnitLessThan(String value) {
            addCriterion("ASSETS_UNIT <", value, "assetsUnit");
            return (Criteria) this;
        }

        public Criteria andAssetsUnitLessThanOrEqualTo(String value) {
            addCriterion("ASSETS_UNIT <=", value, "assetsUnit");
            return (Criteria) this;
        }

        public Criteria andAssetsUnitLike(String value) {
            addCriterion("ASSETS_UNIT like", value, "assetsUnit");
            return (Criteria) this;
        }

        public Criteria andAssetsUnitNotLike(String value) {
            addCriterion("ASSETS_UNIT not like", value, "assetsUnit");
            return (Criteria) this;
        }

        public Criteria andAssetsUnitIn(List<String> values) {
            addCriterion("ASSETS_UNIT in", values, "assetsUnit");
            return (Criteria) this;
        }

        public Criteria andAssetsUnitNotIn(List<String> values) {
            addCriterion("ASSETS_UNIT not in", values, "assetsUnit");
            return (Criteria) this;
        }

        public Criteria andAssetsUnitBetween(String value1, String value2) {
            addCriterion("ASSETS_UNIT between", value1, value2, "assetsUnit");
            return (Criteria) this;
        }

        public Criteria andAssetsUnitNotBetween(String value1, String value2) {
            addCriterion("ASSETS_UNIT not between", value1, value2, "assetsUnit");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("BALANCE is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("BALANCE is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(long value) {
            addCriterion("BALANCE =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(long value) {
            addCriterion("BALANCE <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(long value) {
            addCriterion("BALANCE >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(long value) {
            addCriterion("BALANCE >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(long value) {
            addCriterion("BALANCE <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(long value) {
            addCriterion("BALANCE <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<Long> values) {
            addCriterion("BALANCE in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<Long> values) {
            addCriterion("BALANCE not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(long value1, long value2) {
            addCriterion("BALANCE between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(long value1, long value2) {
            addCriterion("BALANCE not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andAssetsStatusIsNull() {
            addCriterion("ASSETS_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andAssetsStatusIsNotNull() {
            addCriterion("ASSETS_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andAssetsStatusEqualTo(String value) {
            addCriterion("ASSETS_STATUS =", value, "assetsStatus");
            return (Criteria) this;
        }

        public Criteria andAssetsStatusNotEqualTo(String value) {
            addCriterion("ASSETS_STATUS <>", value, "assetsStatus");
            return (Criteria) this;
        }

        public Criteria andAssetsStatusGreaterThan(String value) {
            addCriterion("ASSETS_STATUS >", value, "assetsStatus");
            return (Criteria) this;
        }

        public Criteria andAssetsStatusGreaterThanOrEqualTo(String value) {
            addCriterion("ASSETS_STATUS >=", value, "assetsStatus");
            return (Criteria) this;
        }

        public Criteria andAssetsStatusLessThan(String value) {
            addCriterion("ASSETS_STATUS <", value, "assetsStatus");
            return (Criteria) this;
        }

        public Criteria andAssetsStatusLessThanOrEqualTo(String value) {
            addCriterion("ASSETS_STATUS <=", value, "assetsStatus");
            return (Criteria) this;
        }

        public Criteria andAssetsStatusLike(String value) {
            addCriterion("ASSETS_STATUS like", value, "assetsStatus");
            return (Criteria) this;
        }

        public Criteria andAssetsStatusNotLike(String value) {
            addCriterion("ASSETS_STATUS not like", value, "assetsStatus");
            return (Criteria) this;
        }

        public Criteria andAssetsStatusIn(List<String> values) {
            addCriterion("ASSETS_STATUS in", values, "assetsStatus");
            return (Criteria) this;
        }

        public Criteria andAssetsStatusNotIn(List<String> values) {
            addCriterion("ASSETS_STATUS not in", values, "assetsStatus");
            return (Criteria) this;
        }

        public Criteria andAssetsStatusBetween(String value1, String value2) {
            addCriterion("ASSETS_STATUS between", value1, value2, "assetsStatus");
            return (Criteria) this;
        }

        public Criteria andAssetsStatusNotBetween(String value1, String value2) {
            addCriterion("ASSETS_STATUS not between", value1, value2, "assetsStatus");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Timestamp value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Timestamp value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Timestamp value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Timestamp value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Timestamp> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Timestamp> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andChgDateIsNull() {
            addCriterion("CHG_DATE is null");
            return (Criteria) this;
        }

        public Criteria andChgDateIsNotNull() {
            addCriterion("CHG_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andChgDateEqualTo(Timestamp value) {
            addCriterion("CHG_DATE =", value, "chgDate");
            return (Criteria) this;
        }

        public Criteria andChgDateNotEqualTo(Timestamp value) {
            addCriterion("CHG_DATE <>", value, "chgDate");
            return (Criteria) this;
        }

        public Criteria andChgDateGreaterThan(Timestamp value) {
            addCriterion("CHG_DATE >", value, "chgDate");
            return (Criteria) this;
        }

        public Criteria andChgDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CHG_DATE >=", value, "chgDate");
            return (Criteria) this;
        }

        public Criteria andChgDateLessThan(Timestamp value) {
            addCriterion("CHG_DATE <", value, "chgDate");
            return (Criteria) this;
        }

        public Criteria andChgDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("CHG_DATE <=", value, "chgDate");
            return (Criteria) this;
        }

        public Criteria andChgDateIn(List<Timestamp> values) {
            addCriterion("CHG_DATE in", values, "chgDate");
            return (Criteria) this;
        }

        public Criteria andChgDateNotIn(List<Timestamp> values) {
            addCriterion("CHG_DATE not in", values, "chgDate");
            return (Criteria) this;
        }

        public Criteria andChgDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CHG_DATE between", value1, value2, "chgDate");
            return (Criteria) this;
        }

        public Criteria andChgDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CHG_DATE not between", value1, value2, "chgDate");
            return (Criteria) this;
        }

        public Criteria andChgDescIsNull() {
            addCriterion("CHG_DESC is null");
            return (Criteria) this;
        }

        public Criteria andChgDescIsNotNull() {
            addCriterion("CHG_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andChgDescEqualTo(String value) {
            addCriterion("CHG_DESC =", value, "chgDesc");
            return (Criteria) this;
        }

        public Criteria andChgDescNotEqualTo(String value) {
            addCriterion("CHG_DESC <>", value, "chgDesc");
            return (Criteria) this;
        }

        public Criteria andChgDescGreaterThan(String value) {
            addCriterion("CHG_DESC >", value, "chgDesc");
            return (Criteria) this;
        }

        public Criteria andChgDescGreaterThanOrEqualTo(String value) {
            addCriterion("CHG_DESC >=", value, "chgDesc");
            return (Criteria) this;
        }

        public Criteria andChgDescLessThan(String value) {
            addCriterion("CHG_DESC <", value, "chgDesc");
            return (Criteria) this;
        }

        public Criteria andChgDescLessThanOrEqualTo(String value) {
            addCriterion("CHG_DESC <=", value, "chgDesc");
            return (Criteria) this;
        }

        public Criteria andChgDescLike(String value) {
            addCriterion("CHG_DESC like", value, "chgDesc");
            return (Criteria) this;
        }

        public Criteria andChgDescNotLike(String value) {
            addCriterion("CHG_DESC not like", value, "chgDesc");
            return (Criteria) this;
        }

        public Criteria andChgDescIn(List<String> values) {
            addCriterion("CHG_DESC in", values, "chgDesc");
            return (Criteria) this;
        }

        public Criteria andChgDescNotIn(List<String> values) {
            addCriterion("CHG_DESC not in", values, "chgDesc");
            return (Criteria) this;
        }

        public Criteria andChgDescBetween(String value1, String value2) {
            addCriterion("CHG_DESC between", value1, value2, "chgDesc");
            return (Criteria) this;
        }

        public Criteria andChgDescNotBetween(String value1, String value2) {
            addCriterion("CHG_DESC not between", value1, value2, "chgDesc");
            return (Criteria) this;
        }

        public Criteria andTotalExpenditureIsNull() {
            addCriterion("TOTAL_EXPENDITURE is null");
            return (Criteria) this;
        }

        public Criteria andTotalExpenditureIsNotNull() {
            addCriterion("TOTAL_EXPENDITURE is not null");
            return (Criteria) this;
        }

        public Criteria andTotalExpenditureEqualTo(long value) {
            addCriterion("TOTAL_EXPENDITURE =", value, "totalExpenditure");
            return (Criteria) this;
        }

        public Criteria andTotalExpenditureNotEqualTo(long value) {
            addCriterion("TOTAL_EXPENDITURE <>", value, "totalExpenditure");
            return (Criteria) this;
        }

        public Criteria andTotalExpenditureGreaterThan(long value) {
            addCriterion("TOTAL_EXPENDITURE >", value, "totalExpenditure");
            return (Criteria) this;
        }

        public Criteria andTotalExpenditureGreaterThanOrEqualTo(long value) {
            addCriterion("TOTAL_EXPENDITURE >=", value, "totalExpenditure");
            return (Criteria) this;
        }

        public Criteria andTotalExpenditureLessThan(long value) {
            addCriterion("TOTAL_EXPENDITURE <", value, "totalExpenditure");
            return (Criteria) this;
        }

        public Criteria andTotalExpenditureLessThanOrEqualTo(long value) {
            addCriterion("TOTAL_EXPENDITURE <=", value, "totalExpenditure");
            return (Criteria) this;
        }

        public Criteria andTotalExpenditureIn(List<Long> values) {
            addCriterion("TOTAL_EXPENDITURE in", values, "totalExpenditure");
            return (Criteria) this;
        }

        public Criteria andTotalExpenditureNotIn(List<Long> values) {
            addCriterion("TOTAL_EXPENDITURE not in", values, "totalExpenditure");
            return (Criteria) this;
        }

        public Criteria andTotalExpenditureBetween(long value1, long value2) {
            addCriterion("TOTAL_EXPENDITURE between", value1, value2, "totalExpenditure");
            return (Criteria) this;
        }

        public Criteria andTotalExpenditureNotBetween(long value1, long value2) {
            addCriterion("TOTAL_EXPENDITURE not between", value1, value2, "totalExpenditure");
            return (Criteria) this;
        }

        public Criteria andTotalIncomeIsNull() {
            addCriterion("TOTAL_INCOME is null");
            return (Criteria) this;
        }

        public Criteria andTotalIncomeIsNotNull() {
            addCriterion("TOTAL_INCOME is not null");
            return (Criteria) this;
        }

        public Criteria andTotalIncomeEqualTo(long value) {
            addCriterion("TOTAL_INCOME =", value, "totalIncome");
            return (Criteria) this;
        }

        public Criteria andTotalIncomeNotEqualTo(long value) {
            addCriterion("TOTAL_INCOME <>", value, "totalIncome");
            return (Criteria) this;
        }

        public Criteria andTotalIncomeGreaterThan(long value) {
            addCriterion("TOTAL_INCOME >", value, "totalIncome");
            return (Criteria) this;
        }

        public Criteria andTotalIncomeGreaterThanOrEqualTo(long value) {
            addCriterion("TOTAL_INCOME >=", value, "totalIncome");
            return (Criteria) this;
        }

        public Criteria andTotalIncomeLessThan(long value) {
            addCriterion("TOTAL_INCOME <", value, "totalIncome");
            return (Criteria) this;
        }

        public Criteria andTotalIncomeLessThanOrEqualTo(long value) {
            addCriterion("TOTAL_INCOME <=", value, "totalIncome");
            return (Criteria) this;
        }

        public Criteria andTotalIncomeIn(List<Long> values) {
            addCriterion("TOTAL_INCOME in", values, "totalIncome");
            return (Criteria) this;
        }

        public Criteria andTotalIncomeNotIn(List<Long> values) {
            addCriterion("TOTAL_INCOME not in", values, "totalIncome");
            return (Criteria) this;
        }

        public Criteria andTotalIncomeBetween(long value1, long value2) {
            addCriterion("TOTAL_INCOME between", value1, value2, "totalIncome");
            return (Criteria) this;
        }

        public Criteria andTotalIncomeNotBetween(long value1, long value2) {
            addCriterion("TOTAL_INCOME not between", value1, value2, "totalIncome");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}