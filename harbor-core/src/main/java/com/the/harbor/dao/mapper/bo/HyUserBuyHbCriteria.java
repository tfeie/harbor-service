package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HyUserBuyHbCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyUserBuyHbCriteria() {
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

        public Criteria andBuyOrderIdIsNull() {
            addCriterion("BUY_ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andBuyOrderIdIsNotNull() {
            addCriterion("BUY_ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBuyOrderIdEqualTo(String value) {
            addCriterion("BUY_ORDER_ID =", value, "buyOrderId");
            return (Criteria) this;
        }

        public Criteria andBuyOrderIdNotEqualTo(String value) {
            addCriterion("BUY_ORDER_ID <>", value, "buyOrderId");
            return (Criteria) this;
        }

        public Criteria andBuyOrderIdGreaterThan(String value) {
            addCriterion("BUY_ORDER_ID >", value, "buyOrderId");
            return (Criteria) this;
        }

        public Criteria andBuyOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("BUY_ORDER_ID >=", value, "buyOrderId");
            return (Criteria) this;
        }

        public Criteria andBuyOrderIdLessThan(String value) {
            addCriterion("BUY_ORDER_ID <", value, "buyOrderId");
            return (Criteria) this;
        }

        public Criteria andBuyOrderIdLessThanOrEqualTo(String value) {
            addCriterion("BUY_ORDER_ID <=", value, "buyOrderId");
            return (Criteria) this;
        }

        public Criteria andBuyOrderIdLike(String value) {
            addCriterion("BUY_ORDER_ID like", value, "buyOrderId");
            return (Criteria) this;
        }

        public Criteria andBuyOrderIdNotLike(String value) {
            addCriterion("BUY_ORDER_ID not like", value, "buyOrderId");
            return (Criteria) this;
        }

        public Criteria andBuyOrderIdIn(List<String> values) {
            addCriterion("BUY_ORDER_ID in", values, "buyOrderId");
            return (Criteria) this;
        }

        public Criteria andBuyOrderIdNotIn(List<String> values) {
            addCriterion("BUY_ORDER_ID not in", values, "buyOrderId");
            return (Criteria) this;
        }

        public Criteria andBuyOrderIdBetween(String value1, String value2) {
            addCriterion("BUY_ORDER_ID between", value1, value2, "buyOrderId");
            return (Criteria) this;
        }

        public Criteria andBuyOrderIdNotBetween(String value1, String value2) {
            addCriterion("BUY_ORDER_ID not between", value1, value2, "buyOrderId");
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

        public Criteria andBuyAmountIsNull() {
            addCriterion("BUY_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andBuyAmountIsNotNull() {
            addCriterion("BUY_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andBuyAmountEqualTo(int value) {
            addCriterion("BUY_AMOUNT =", value, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountNotEqualTo(int value) {
            addCriterion("BUY_AMOUNT <>", value, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountGreaterThan(int value) {
            addCriterion("BUY_AMOUNT >", value, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountGreaterThanOrEqualTo(int value) {
            addCriterion("BUY_AMOUNT >=", value, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountLessThan(int value) {
            addCriterion("BUY_AMOUNT <", value, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountLessThanOrEqualTo(int value) {
            addCriterion("BUY_AMOUNT <=", value, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountIn(List<Integer> values) {
            addCriterion("BUY_AMOUNT in", values, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountNotIn(List<Integer> values) {
            addCriterion("BUY_AMOUNT not in", values, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountBetween(int value1, int value2) {
            addCriterion("BUY_AMOUNT between", value1, value2, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andBuyAmountNotBetween(int value1, int value2) {
            addCriterion("BUY_AMOUNT not between", value1, value2, "buyAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNull() {
            addCriterion("PAY_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNotNull() {
            addCriterion("PAY_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountEqualTo(long value) {
            addCriterion("PAY_AMOUNT =", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotEqualTo(long value) {
            addCriterion("PAY_AMOUNT <>", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThan(long value) {
            addCriterion("PAY_AMOUNT >", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThanOrEqualTo(long value) {
            addCriterion("PAY_AMOUNT >=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThan(long value) {
            addCriterion("PAY_AMOUNT <", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThanOrEqualTo(long value) {
            addCriterion("PAY_AMOUNT <=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIn(List<Long> values) {
            addCriterion("PAY_AMOUNT in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotIn(List<Long> values) {
            addCriterion("PAY_AMOUNT not in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountBetween(long value1, long value2) {
            addCriterion("PAY_AMOUNT between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotBetween(long value1, long value2) {
            addCriterion("PAY_AMOUNT not between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andBuyDateIsNull() {
            addCriterion("BUY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andBuyDateIsNotNull() {
            addCriterion("BUY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andBuyDateEqualTo(Timestamp value) {
            addCriterion("BUY_DATE =", value, "buyDate");
            return (Criteria) this;
        }

        public Criteria andBuyDateNotEqualTo(Timestamp value) {
            addCriterion("BUY_DATE <>", value, "buyDate");
            return (Criteria) this;
        }

        public Criteria andBuyDateGreaterThan(Timestamp value) {
            addCriterion("BUY_DATE >", value, "buyDate");
            return (Criteria) this;
        }

        public Criteria andBuyDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("BUY_DATE >=", value, "buyDate");
            return (Criteria) this;
        }

        public Criteria andBuyDateLessThan(Timestamp value) {
            addCriterion("BUY_DATE <", value, "buyDate");
            return (Criteria) this;
        }

        public Criteria andBuyDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("BUY_DATE <=", value, "buyDate");
            return (Criteria) this;
        }

        public Criteria andBuyDateIn(List<Timestamp> values) {
            addCriterion("BUY_DATE in", values, "buyDate");
            return (Criteria) this;
        }

        public Criteria andBuyDateNotIn(List<Timestamp> values) {
            addCriterion("BUY_DATE not in", values, "buyDate");
            return (Criteria) this;
        }

        public Criteria andBuyDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("BUY_DATE between", value1, value2, "buyDate");
            return (Criteria) this;
        }

        public Criteria andBuyDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("BUY_DATE not between", value1, value2, "buyDate");
            return (Criteria) this;
        }

        public Criteria andFromSignIsNull() {
            addCriterion("FROM_SIGN is null");
            return (Criteria) this;
        }

        public Criteria andFromSignIsNotNull() {
            addCriterion("FROM_SIGN is not null");
            return (Criteria) this;
        }

        public Criteria andFromSignEqualTo(String value) {
            addCriterion("FROM_SIGN =", value, "fromSign");
            return (Criteria) this;
        }

        public Criteria andFromSignNotEqualTo(String value) {
            addCriterion("FROM_SIGN <>", value, "fromSign");
            return (Criteria) this;
        }

        public Criteria andFromSignGreaterThan(String value) {
            addCriterion("FROM_SIGN >", value, "fromSign");
            return (Criteria) this;
        }

        public Criteria andFromSignGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_SIGN >=", value, "fromSign");
            return (Criteria) this;
        }

        public Criteria andFromSignLessThan(String value) {
            addCriterion("FROM_SIGN <", value, "fromSign");
            return (Criteria) this;
        }

        public Criteria andFromSignLessThanOrEqualTo(String value) {
            addCriterion("FROM_SIGN <=", value, "fromSign");
            return (Criteria) this;
        }

        public Criteria andFromSignLike(String value) {
            addCriterion("FROM_SIGN like", value, "fromSign");
            return (Criteria) this;
        }

        public Criteria andFromSignNotLike(String value) {
            addCriterion("FROM_SIGN not like", value, "fromSign");
            return (Criteria) this;
        }

        public Criteria andFromSignIn(List<String> values) {
            addCriterion("FROM_SIGN in", values, "fromSign");
            return (Criteria) this;
        }

        public Criteria andFromSignNotIn(List<String> values) {
            addCriterion("FROM_SIGN not in", values, "fromSign");
            return (Criteria) this;
        }

        public Criteria andFromSignBetween(String value1, String value2) {
            addCriterion("FROM_SIGN between", value1, value2, "fromSign");
            return (Criteria) this;
        }

        public Criteria andFromSignNotBetween(String value1, String value2) {
            addCriterion("FROM_SIGN not between", value1, value2, "fromSign");
            return (Criteria) this;
        }

        public Criteria andPayOrderIdIsNull() {
            addCriterion("PAY_ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andPayOrderIdIsNotNull() {
            addCriterion("PAY_ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPayOrderIdEqualTo(String value) {
            addCriterion("PAY_ORDER_ID =", value, "payOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOrderIdNotEqualTo(String value) {
            addCriterion("PAY_ORDER_ID <>", value, "payOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOrderIdGreaterThan(String value) {
            addCriterion("PAY_ORDER_ID >", value, "payOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_ORDER_ID >=", value, "payOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOrderIdLessThan(String value) {
            addCriterion("PAY_ORDER_ID <", value, "payOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOrderIdLessThanOrEqualTo(String value) {
            addCriterion("PAY_ORDER_ID <=", value, "payOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOrderIdLike(String value) {
            addCriterion("PAY_ORDER_ID like", value, "payOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOrderIdNotLike(String value) {
            addCriterion("PAY_ORDER_ID not like", value, "payOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOrderIdIn(List<String> values) {
            addCriterion("PAY_ORDER_ID in", values, "payOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOrderIdNotIn(List<String> values) {
            addCriterion("PAY_ORDER_ID not in", values, "payOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOrderIdBetween(String value1, String value2) {
            addCriterion("PAY_ORDER_ID between", value1, value2, "payOrderId");
            return (Criteria) this;
        }

        public Criteria andPayOrderIdNotBetween(String value1, String value2) {
            addCriterion("PAY_ORDER_ID not between", value1, value2, "payOrderId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
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