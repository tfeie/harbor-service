package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HyGoJoinCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyGoJoinCriteria() {
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

        public Criteria andOrderIdIsNull() {
            addCriterion("ORDER_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("ORDER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("ORDER_ID =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("ORDER_ID <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("ORDER_ID >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_ID >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("ORDER_ID <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("ORDER_ID <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("ORDER_ID like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("ORDER_ID not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("ORDER_ID in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("ORDER_ID not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("ORDER_ID between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("ORDER_ID not between", value1, value2, "orderId");
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

        public Criteria andGoIdIsNull() {
            addCriterion("GO_ID is null");
            return (Criteria) this;
        }

        public Criteria andGoIdIsNotNull() {
            addCriterion("GO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGoIdEqualTo(String value) {
            addCriterion("GO_ID =", value, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdNotEqualTo(String value) {
            addCriterion("GO_ID <>", value, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdGreaterThan(String value) {
            addCriterion("GO_ID >", value, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdGreaterThanOrEqualTo(String value) {
            addCriterion("GO_ID >=", value, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdLessThan(String value) {
            addCriterion("GO_ID <", value, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdLessThanOrEqualTo(String value) {
            addCriterion("GO_ID <=", value, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdLike(String value) {
            addCriterion("GO_ID like", value, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdNotLike(String value) {
            addCriterion("GO_ID not like", value, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdIn(List<String> values) {
            addCriterion("GO_ID in", values, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdNotIn(List<String> values) {
            addCriterion("GO_ID not in", values, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdBetween(String value1, String value2) {
            addCriterion("GO_ID between", value1, value2, "goId");
            return (Criteria) this;
        }

        public Criteria andGoIdNotBetween(String value1, String value2) {
            addCriterion("GO_ID not between", value1, value2, "goId");
            return (Criteria) this;
        }

        public Criteria andGoTypeIsNull() {
            addCriterion("GO_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andGoTypeIsNotNull() {
            addCriterion("GO_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andGoTypeEqualTo(String value) {
            addCriterion("GO_TYPE =", value, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeNotEqualTo(String value) {
            addCriterion("GO_TYPE <>", value, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeGreaterThan(String value) {
            addCriterion("GO_TYPE >", value, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeGreaterThanOrEqualTo(String value) {
            addCriterion("GO_TYPE >=", value, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeLessThan(String value) {
            addCriterion("GO_TYPE <", value, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeLessThanOrEqualTo(String value) {
            addCriterion("GO_TYPE <=", value, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeLike(String value) {
            addCriterion("GO_TYPE like", value, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeNotLike(String value) {
            addCriterion("GO_TYPE not like", value, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeIn(List<String> values) {
            addCriterion("GO_TYPE in", values, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeNotIn(List<String> values) {
            addCriterion("GO_TYPE not in", values, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeBetween(String value1, String value2) {
            addCriterion("GO_TYPE between", value1, value2, "goType");
            return (Criteria) this;
        }

        public Criteria andGoTypeNotBetween(String value1, String value2) {
            addCriterion("GO_TYPE not between", value1, value2, "goType");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("ORDER_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("ORDER_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(String value) {
            addCriterion("ORDER_STATUS =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(String value) {
            addCriterion("ORDER_STATUS <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(String value) {
            addCriterion("ORDER_STATUS >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(String value) {
            addCriterion("ORDER_STATUS >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(String value) {
            addCriterion("ORDER_STATUS <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(String value) {
            addCriterion("ORDER_STATUS <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLike(String value) {
            addCriterion("ORDER_STATUS like", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotLike(String value) {
            addCriterion("ORDER_STATUS not like", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<String> values) {
            addCriterion("ORDER_STATUS in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<String> values) {
            addCriterion("ORDER_STATUS not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(String value1, String value2) {
            addCriterion("ORDER_STATUS between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(String value1, String value2) {
            addCriterion("ORDER_STATUS not between", value1, value2, "orderStatus");
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

        public Criteria andStsDateIsNull() {
            addCriterion("STS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andStsDateIsNotNull() {
            addCriterion("STS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andStsDateEqualTo(Timestamp value) {
            addCriterion("STS_DATE =", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateNotEqualTo(Timestamp value) {
            addCriterion("STS_DATE <>", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateGreaterThan(Timestamp value) {
            addCriterion("STS_DATE >", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("STS_DATE >=", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateLessThan(Timestamp value) {
            addCriterion("STS_DATE <", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("STS_DATE <=", value, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateIn(List<Timestamp> values) {
            addCriterion("STS_DATE in", values, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateNotIn(List<Timestamp> values) {
            addCriterion("STS_DATE not in", values, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("STS_DATE between", value1, value2, "stsDate");
            return (Criteria) this;
        }

        public Criteria andStsDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("STS_DATE not between", value1, value2, "stsDate");
            return (Criteria) this;
        }

        public Criteria andHelpValueIsNull() {
            addCriterion("HELP_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andHelpValueIsNotNull() {
            addCriterion("HELP_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andHelpValueEqualTo(String value) {
            addCriterion("HELP_VALUE =", value, "helpValue");
            return (Criteria) this;
        }

        public Criteria andHelpValueNotEqualTo(String value) {
            addCriterion("HELP_VALUE <>", value, "helpValue");
            return (Criteria) this;
        }

        public Criteria andHelpValueGreaterThan(String value) {
            addCriterion("HELP_VALUE >", value, "helpValue");
            return (Criteria) this;
        }

        public Criteria andHelpValueGreaterThanOrEqualTo(String value) {
            addCriterion("HELP_VALUE >=", value, "helpValue");
            return (Criteria) this;
        }

        public Criteria andHelpValueLessThan(String value) {
            addCriterion("HELP_VALUE <", value, "helpValue");
            return (Criteria) this;
        }

        public Criteria andHelpValueLessThanOrEqualTo(String value) {
            addCriterion("HELP_VALUE <=", value, "helpValue");
            return (Criteria) this;
        }

        public Criteria andHelpValueLike(String value) {
            addCriterion("HELP_VALUE like", value, "helpValue");
            return (Criteria) this;
        }

        public Criteria andHelpValueNotLike(String value) {
            addCriterion("HELP_VALUE not like", value, "helpValue");
            return (Criteria) this;
        }

        public Criteria andHelpValueIn(List<String> values) {
            addCriterion("HELP_VALUE in", values, "helpValue");
            return (Criteria) this;
        }

        public Criteria andHelpValueNotIn(List<String> values) {
            addCriterion("HELP_VALUE not in", values, "helpValue");
            return (Criteria) this;
        }

        public Criteria andHelpValueBetween(String value1, String value2) {
            addCriterion("HELP_VALUE between", value1, value2, "helpValue");
            return (Criteria) this;
        }

        public Criteria andHelpValueNotBetween(String value1, String value2) {
            addCriterion("HELP_VALUE not between", value1, value2, "helpValue");
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