package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HyUserAssetsTradeCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyUserAssetsTradeCriteria() {
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

        public Criteria andLogIdIsNull() {
            addCriterion("LOG_ID is null");
            return (Criteria) this;
        }

        public Criteria andLogIdIsNotNull() {
            addCriterion("LOG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLogIdEqualTo(String value) {
            addCriterion("LOG_ID =", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotEqualTo(String value) {
            addCriterion("LOG_ID <>", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdGreaterThan(String value) {
            addCriterion("LOG_ID >", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdGreaterThanOrEqualTo(String value) {
            addCriterion("LOG_ID >=", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdLessThan(String value) {
            addCriterion("LOG_ID <", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdLessThanOrEqualTo(String value) {
            addCriterion("LOG_ID <=", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdLike(String value) {
            addCriterion("LOG_ID like", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotLike(String value) {
            addCriterion("LOG_ID not like", value, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdIn(List<String> values) {
            addCriterion("LOG_ID in", values, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotIn(List<String> values) {
            addCriterion("LOG_ID not in", values, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdBetween(String value1, String value2) {
            addCriterion("LOG_ID between", value1, value2, "logId");
            return (Criteria) this;
        }

        public Criteria andLogIdNotBetween(String value1, String value2) {
            addCriterion("LOG_ID not between", value1, value2, "logId");
            return (Criteria) this;
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

        public Criteria andTradeTypeIsNull() {
            addCriterion("TRADE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIsNotNull() {
            addCriterion("TRADE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeEqualTo(String value) {
            addCriterion("TRADE_TYPE =", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotEqualTo(String value) {
            addCriterion("TRADE_TYPE <>", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThan(String value) {
            addCriterion("TRADE_TYPE >", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TRADE_TYPE >=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThan(String value) {
            addCriterion("TRADE_TYPE <", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThanOrEqualTo(String value) {
            addCriterion("TRADE_TYPE <=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLike(String value) {
            addCriterion("TRADE_TYPE like", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotLike(String value) {
            addCriterion("TRADE_TYPE not like", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIn(List<String> values) {
            addCriterion("TRADE_TYPE in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotIn(List<String> values) {
            addCriterion("TRADE_TYPE not in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeBetween(String value1, String value2) {
            addCriterion("TRADE_TYPE between", value1, value2, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotBetween(String value1, String value2) {
            addCriterion("TRADE_TYPE not between", value1, value2, "tradeType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeIsNull() {
            addCriterion("BUSI_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andBusiTypeIsNotNull() {
            addCriterion("BUSI_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andBusiTypeEqualTo(String value) {
            addCriterion("BUSI_TYPE =", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeNotEqualTo(String value) {
            addCriterion("BUSI_TYPE <>", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeGreaterThan(String value) {
            addCriterion("BUSI_TYPE >", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeGreaterThanOrEqualTo(String value) {
            addCriterion("BUSI_TYPE >=", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeLessThan(String value) {
            addCriterion("BUSI_TYPE <", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeLessThanOrEqualTo(String value) {
            addCriterion("BUSI_TYPE <=", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeLike(String value) {
            addCriterion("BUSI_TYPE like", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeNotLike(String value) {
            addCriterion("BUSI_TYPE not like", value, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeIn(List<String> values) {
            addCriterion("BUSI_TYPE in", values, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeNotIn(List<String> values) {
            addCriterion("BUSI_TYPE not in", values, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeBetween(String value1, String value2) {
            addCriterion("BUSI_TYPE between", value1, value2, "busiType");
            return (Criteria) this;
        }

        public Criteria andBusiTypeNotBetween(String value1, String value2) {
            addCriterion("BUSI_TYPE not between", value1, value2, "busiType");
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

        public Criteria andLastBalanceIsNull() {
            addCriterion("LAST_BALANCE is null");
            return (Criteria) this;
        }

        public Criteria andLastBalanceIsNotNull() {
            addCriterion("LAST_BALANCE is not null");
            return (Criteria) this;
        }

        public Criteria andLastBalanceEqualTo(long value) {
            addCriterion("LAST_BALANCE =", value, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceNotEqualTo(long value) {
            addCriterion("LAST_BALANCE <>", value, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceGreaterThan(long value) {
            addCriterion("LAST_BALANCE >", value, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceGreaterThanOrEqualTo(long value) {
            addCriterion("LAST_BALANCE >=", value, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceLessThan(long value) {
            addCriterion("LAST_BALANCE <", value, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceLessThanOrEqualTo(long value) {
            addCriterion("LAST_BALANCE <=", value, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceIn(List<Long> values) {
            addCriterion("LAST_BALANCE in", values, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceNotIn(List<Long> values) {
            addCriterion("LAST_BALANCE not in", values, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceBetween(long value1, long value2) {
            addCriterion("LAST_BALANCE between", value1, value2, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andLastBalanceNotBetween(long value1, long value2) {
            addCriterion("LAST_BALANCE not between", value1, value2, "lastBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceIsNull() {
            addCriterion("CURRENT_BALANCE is null");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceIsNotNull() {
            addCriterion("CURRENT_BALANCE is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceEqualTo(long value) {
            addCriterion("CURRENT_BALANCE =", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceNotEqualTo(long value) {
            addCriterion("CURRENT_BALANCE <>", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceGreaterThan(long value) {
            addCriterion("CURRENT_BALANCE >", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceGreaterThanOrEqualTo(long value) {
            addCriterion("CURRENT_BALANCE >=", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceLessThan(long value) {
            addCriterion("CURRENT_BALANCE <", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceLessThanOrEqualTo(long value) {
            addCriterion("CURRENT_BALANCE <=", value, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceIn(List<Long> values) {
            addCriterion("CURRENT_BALANCE in", values, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceNotIn(List<Long> values) {
            addCriterion("CURRENT_BALANCE not in", values, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceBetween(long value1, long value2) {
            addCriterion("CURRENT_BALANCE between", value1, value2, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andCurrentBalanceNotBetween(long value1, long value2) {
            addCriterion("CURRENT_BALANCE not between", value1, value2, "currentBalance");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNull() {
            addCriterion("SUMMARY is null");
            return (Criteria) this;
        }

        public Criteria andSummaryIsNotNull() {
            addCriterion("SUMMARY is not null");
            return (Criteria) this;
        }

        public Criteria andSummaryEqualTo(String value) {
            addCriterion("SUMMARY =", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotEqualTo(String value) {
            addCriterion("SUMMARY <>", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThan(String value) {
            addCriterion("SUMMARY >", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryGreaterThanOrEqualTo(String value) {
            addCriterion("SUMMARY >=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThan(String value) {
            addCriterion("SUMMARY <", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLessThanOrEqualTo(String value) {
            addCriterion("SUMMARY <=", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryLike(String value) {
            addCriterion("SUMMARY like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotLike(String value) {
            addCriterion("SUMMARY not like", value, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryIn(List<String> values) {
            addCriterion("SUMMARY in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotIn(List<String> values) {
            addCriterion("SUMMARY not in", values, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryBetween(String value1, String value2) {
            addCriterion("SUMMARY between", value1, value2, "summary");
            return (Criteria) this;
        }

        public Criteria andSummaryNotBetween(String value1, String value2) {
            addCriterion("SUMMARY not between", value1, value2, "summary");
            return (Criteria) this;
        }

        public Criteria andTradeDateIsNull() {
            addCriterion("TRADE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andTradeDateIsNotNull() {
            addCriterion("TRADE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andTradeDateEqualTo(Timestamp value) {
            addCriterion("TRADE_DATE =", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotEqualTo(Timestamp value) {
            addCriterion("TRADE_DATE <>", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateGreaterThan(Timestamp value) {
            addCriterion("TRADE_DATE >", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("TRADE_DATE >=", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateLessThan(Timestamp value) {
            addCriterion("TRADE_DATE <", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("TRADE_DATE <=", value, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateIn(List<Timestamp> values) {
            addCriterion("TRADE_DATE in", values, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotIn(List<Timestamp> values) {
            addCriterion("TRADE_DATE not in", values, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("TRADE_DATE between", value1, value2, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andTradeDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("TRADE_DATE not between", value1, value2, "tradeDate");
            return (Criteria) this;
        }

        public Criteria andSourceNoIsNull() {
            addCriterion("SOURCE_NO is null");
            return (Criteria) this;
        }

        public Criteria andSourceNoIsNotNull() {
            addCriterion("SOURCE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andSourceNoEqualTo(String value) {
            addCriterion("SOURCE_NO =", value, "sourceNo");
            return (Criteria) this;
        }

        public Criteria andSourceNoNotEqualTo(String value) {
            addCriterion("SOURCE_NO <>", value, "sourceNo");
            return (Criteria) this;
        }

        public Criteria andSourceNoGreaterThan(String value) {
            addCriterion("SOURCE_NO >", value, "sourceNo");
            return (Criteria) this;
        }

        public Criteria andSourceNoGreaterThanOrEqualTo(String value) {
            addCriterion("SOURCE_NO >=", value, "sourceNo");
            return (Criteria) this;
        }

        public Criteria andSourceNoLessThan(String value) {
            addCriterion("SOURCE_NO <", value, "sourceNo");
            return (Criteria) this;
        }

        public Criteria andSourceNoLessThanOrEqualTo(String value) {
            addCriterion("SOURCE_NO <=", value, "sourceNo");
            return (Criteria) this;
        }

        public Criteria andSourceNoLike(String value) {
            addCriterion("SOURCE_NO like", value, "sourceNo");
            return (Criteria) this;
        }

        public Criteria andSourceNoNotLike(String value) {
            addCriterion("SOURCE_NO not like", value, "sourceNo");
            return (Criteria) this;
        }

        public Criteria andSourceNoIn(List<String> values) {
            addCriterion("SOURCE_NO in", values, "sourceNo");
            return (Criteria) this;
        }

        public Criteria andSourceNoNotIn(List<String> values) {
            addCriterion("SOURCE_NO not in", values, "sourceNo");
            return (Criteria) this;
        }

        public Criteria andSourceNoBetween(String value1, String value2) {
            addCriterion("SOURCE_NO between", value1, value2, "sourceNo");
            return (Criteria) this;
        }

        public Criteria andSourceNoNotBetween(String value1, String value2) {
            addCriterion("SOURCE_NO not between", value1, value2, "sourceNo");
            return (Criteria) this;
        }

        public Criteria andRelUserIdIsNull() {
            addCriterion("REL_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andRelUserIdIsNotNull() {
            addCriterion("REL_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRelUserIdEqualTo(String value) {
            addCriterion("REL_USER_ID =", value, "relUserId");
            return (Criteria) this;
        }

        public Criteria andRelUserIdNotEqualTo(String value) {
            addCriterion("REL_USER_ID <>", value, "relUserId");
            return (Criteria) this;
        }

        public Criteria andRelUserIdGreaterThan(String value) {
            addCriterion("REL_USER_ID >", value, "relUserId");
            return (Criteria) this;
        }

        public Criteria andRelUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("REL_USER_ID >=", value, "relUserId");
            return (Criteria) this;
        }

        public Criteria andRelUserIdLessThan(String value) {
            addCriterion("REL_USER_ID <", value, "relUserId");
            return (Criteria) this;
        }

        public Criteria andRelUserIdLessThanOrEqualTo(String value) {
            addCriterion("REL_USER_ID <=", value, "relUserId");
            return (Criteria) this;
        }

        public Criteria andRelUserIdLike(String value) {
            addCriterion("REL_USER_ID like", value, "relUserId");
            return (Criteria) this;
        }

        public Criteria andRelUserIdNotLike(String value) {
            addCriterion("REL_USER_ID not like", value, "relUserId");
            return (Criteria) this;
        }

        public Criteria andRelUserIdIn(List<String> values) {
            addCriterion("REL_USER_ID in", values, "relUserId");
            return (Criteria) this;
        }

        public Criteria andRelUserIdNotIn(List<String> values) {
            addCriterion("REL_USER_ID not in", values, "relUserId");
            return (Criteria) this;
        }

        public Criteria andRelUserIdBetween(String value1, String value2) {
            addCriterion("REL_USER_ID between", value1, value2, "relUserId");
            return (Criteria) this;
        }

        public Criteria andRelUserIdNotBetween(String value1, String value2) {
            addCriterion("REL_USER_ID not between", value1, value2, "relUserId");
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