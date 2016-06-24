package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HyBeGiveHbCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyBeGiveHbCriteria() {
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

        public Criteria andGiveIdIsNull() {
            addCriterion("GIVE_ID is null");
            return (Criteria) this;
        }

        public Criteria andGiveIdIsNotNull() {
            addCriterion("GIVE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGiveIdEqualTo(String value) {
            addCriterion("GIVE_ID =", value, "giveId");
            return (Criteria) this;
        }

        public Criteria andGiveIdNotEqualTo(String value) {
            addCriterion("GIVE_ID <>", value, "giveId");
            return (Criteria) this;
        }

        public Criteria andGiveIdGreaterThan(String value) {
            addCriterion("GIVE_ID >", value, "giveId");
            return (Criteria) this;
        }

        public Criteria andGiveIdGreaterThanOrEqualTo(String value) {
            addCriterion("GIVE_ID >=", value, "giveId");
            return (Criteria) this;
        }

        public Criteria andGiveIdLessThan(String value) {
            addCriterion("GIVE_ID <", value, "giveId");
            return (Criteria) this;
        }

        public Criteria andGiveIdLessThanOrEqualTo(String value) {
            addCriterion("GIVE_ID <=", value, "giveId");
            return (Criteria) this;
        }

        public Criteria andGiveIdLike(String value) {
            addCriterion("GIVE_ID like", value, "giveId");
            return (Criteria) this;
        }

        public Criteria andGiveIdNotLike(String value) {
            addCriterion("GIVE_ID not like", value, "giveId");
            return (Criteria) this;
        }

        public Criteria andGiveIdIn(List<String> values) {
            addCriterion("GIVE_ID in", values, "giveId");
            return (Criteria) this;
        }

        public Criteria andGiveIdNotIn(List<String> values) {
            addCriterion("GIVE_ID not in", values, "giveId");
            return (Criteria) this;
        }

        public Criteria andGiveIdBetween(String value1, String value2) {
            addCriterion("GIVE_ID between", value1, value2, "giveId");
            return (Criteria) this;
        }

        public Criteria andGiveIdNotBetween(String value1, String value2) {
            addCriterion("GIVE_ID not between", value1, value2, "giveId");
            return (Criteria) this;
        }

        public Criteria andBeIdIsNull() {
            addCriterion("BE_ID is null");
            return (Criteria) this;
        }

        public Criteria andBeIdIsNotNull() {
            addCriterion("BE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBeIdEqualTo(String value) {
            addCriterion("BE_ID =", value, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdNotEqualTo(String value) {
            addCriterion("BE_ID <>", value, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdGreaterThan(String value) {
            addCriterion("BE_ID >", value, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdGreaterThanOrEqualTo(String value) {
            addCriterion("BE_ID >=", value, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdLessThan(String value) {
            addCriterion("BE_ID <", value, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdLessThanOrEqualTo(String value) {
            addCriterion("BE_ID <=", value, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdLike(String value) {
            addCriterion("BE_ID like", value, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdNotLike(String value) {
            addCriterion("BE_ID not like", value, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdIn(List<String> values) {
            addCriterion("BE_ID in", values, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdNotIn(List<String> values) {
            addCriterion("BE_ID not in", values, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdBetween(String value1, String value2) {
            addCriterion("BE_ID between", value1, value2, "beId");
            return (Criteria) this;
        }

        public Criteria andBeIdNotBetween(String value1, String value2) {
            addCriterion("BE_ID not between", value1, value2, "beId");
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

        public Criteria andPayUserIdIsNull() {
            addCriterion("PAY_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andPayUserIdIsNotNull() {
            addCriterion("PAY_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPayUserIdEqualTo(String value) {
            addCriterion("PAY_USER_ID =", value, "payUserId");
            return (Criteria) this;
        }

        public Criteria andPayUserIdNotEqualTo(String value) {
            addCriterion("PAY_USER_ID <>", value, "payUserId");
            return (Criteria) this;
        }

        public Criteria andPayUserIdGreaterThan(String value) {
            addCriterion("PAY_USER_ID >", value, "payUserId");
            return (Criteria) this;
        }

        public Criteria andPayUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_USER_ID >=", value, "payUserId");
            return (Criteria) this;
        }

        public Criteria andPayUserIdLessThan(String value) {
            addCriterion("PAY_USER_ID <", value, "payUserId");
            return (Criteria) this;
        }

        public Criteria andPayUserIdLessThanOrEqualTo(String value) {
            addCriterion("PAY_USER_ID <=", value, "payUserId");
            return (Criteria) this;
        }

        public Criteria andPayUserIdLike(String value) {
            addCriterion("PAY_USER_ID like", value, "payUserId");
            return (Criteria) this;
        }

        public Criteria andPayUserIdNotLike(String value) {
            addCriterion("PAY_USER_ID not like", value, "payUserId");
            return (Criteria) this;
        }

        public Criteria andPayUserIdIn(List<String> values) {
            addCriterion("PAY_USER_ID in", values, "payUserId");
            return (Criteria) this;
        }

        public Criteria andPayUserIdNotIn(List<String> values) {
            addCriterion("PAY_USER_ID not in", values, "payUserId");
            return (Criteria) this;
        }

        public Criteria andPayUserIdBetween(String value1, String value2) {
            addCriterion("PAY_USER_ID between", value1, value2, "payUserId");
            return (Criteria) this;
        }

        public Criteria andPayUserIdNotBetween(String value1, String value2) {
            addCriterion("PAY_USER_ID not between", value1, value2, "payUserId");
            return (Criteria) this;
        }

        public Criteria andTargetUserIdIsNull() {
            addCriterion("TARGET_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andTargetUserIdIsNotNull() {
            addCriterion("TARGET_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTargetUserIdEqualTo(String value) {
            addCriterion("TARGET_USER_ID =", value, "targetUserId");
            return (Criteria) this;
        }

        public Criteria andTargetUserIdNotEqualTo(String value) {
            addCriterion("TARGET_USER_ID <>", value, "targetUserId");
            return (Criteria) this;
        }

        public Criteria andTargetUserIdGreaterThan(String value) {
            addCriterion("TARGET_USER_ID >", value, "targetUserId");
            return (Criteria) this;
        }

        public Criteria andTargetUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("TARGET_USER_ID >=", value, "targetUserId");
            return (Criteria) this;
        }

        public Criteria andTargetUserIdLessThan(String value) {
            addCriterion("TARGET_USER_ID <", value, "targetUserId");
            return (Criteria) this;
        }

        public Criteria andTargetUserIdLessThanOrEqualTo(String value) {
            addCriterion("TARGET_USER_ID <=", value, "targetUserId");
            return (Criteria) this;
        }

        public Criteria andTargetUserIdLike(String value) {
            addCriterion("TARGET_USER_ID like", value, "targetUserId");
            return (Criteria) this;
        }

        public Criteria andTargetUserIdNotLike(String value) {
            addCriterion("TARGET_USER_ID not like", value, "targetUserId");
            return (Criteria) this;
        }

        public Criteria andTargetUserIdIn(List<String> values) {
            addCriterion("TARGET_USER_ID in", values, "targetUserId");
            return (Criteria) this;
        }

        public Criteria andTargetUserIdNotIn(List<String> values) {
            addCriterion("TARGET_USER_ID not in", values, "targetUserId");
            return (Criteria) this;
        }

        public Criteria andTargetUserIdBetween(String value1, String value2) {
            addCriterion("TARGET_USER_ID between", value1, value2, "targetUserId");
            return (Criteria) this;
        }

        public Criteria andTargetUserIdNotBetween(String value1, String value2) {
            addCriterion("TARGET_USER_ID not between", value1, value2, "targetUserId");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(long value) {
            addCriterion("AMOUNT =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(long value) {
            addCriterion("AMOUNT <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(long value) {
            addCriterion("AMOUNT >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(long value) {
            addCriterion("AMOUNT >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(long value) {
            addCriterion("AMOUNT <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(long value) {
            addCriterion("AMOUNT <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Long> values) {
            addCriterion("AMOUNT in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Long> values) {
            addCriterion("AMOUNT not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(long value1, long value2) {
            addCriterion("AMOUNT between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(long value1, long value2) {
            addCriterion("AMOUNT not between", value1, value2, "amount");
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