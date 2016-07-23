package com.the.harbor.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class HyCfgCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyCfgCriteria() {
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

        public Criteria andCfgKeyIsNull() {
            addCriterion("CFG_KEY is null");
            return (Criteria) this;
        }

        public Criteria andCfgKeyIsNotNull() {
            addCriterion("CFG_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andCfgKeyEqualTo(String value) {
            addCriterion("CFG_KEY =", value, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyNotEqualTo(String value) {
            addCriterion("CFG_KEY <>", value, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyGreaterThan(String value) {
            addCriterion("CFG_KEY >", value, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyGreaterThanOrEqualTo(String value) {
            addCriterion("CFG_KEY >=", value, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyLessThan(String value) {
            addCriterion("CFG_KEY <", value, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyLessThanOrEqualTo(String value) {
            addCriterion("CFG_KEY <=", value, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyLike(String value) {
            addCriterion("CFG_KEY like", value, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyNotLike(String value) {
            addCriterion("CFG_KEY not like", value, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyIn(List<String> values) {
            addCriterion("CFG_KEY in", values, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyNotIn(List<String> values) {
            addCriterion("CFG_KEY not in", values, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyBetween(String value1, String value2) {
            addCriterion("CFG_KEY between", value1, value2, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgKeyNotBetween(String value1, String value2) {
            addCriterion("CFG_KEY not between", value1, value2, "cfgKey");
            return (Criteria) this;
        }

        public Criteria andCfgValueIsNull() {
            addCriterion("CFG_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andCfgValueIsNotNull() {
            addCriterion("CFG_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andCfgValueEqualTo(String value) {
            addCriterion("CFG_VALUE =", value, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueNotEqualTo(String value) {
            addCriterion("CFG_VALUE <>", value, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueGreaterThan(String value) {
            addCriterion("CFG_VALUE >", value, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueGreaterThanOrEqualTo(String value) {
            addCriterion("CFG_VALUE >=", value, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueLessThan(String value) {
            addCriterion("CFG_VALUE <", value, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueLessThanOrEqualTo(String value) {
            addCriterion("CFG_VALUE <=", value, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueLike(String value) {
            addCriterion("CFG_VALUE like", value, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueNotLike(String value) {
            addCriterion("CFG_VALUE not like", value, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueIn(List<String> values) {
            addCriterion("CFG_VALUE in", values, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueNotIn(List<String> values) {
            addCriterion("CFG_VALUE not in", values, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueBetween(String value1, String value2) {
            addCriterion("CFG_VALUE between", value1, value2, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgValueNotBetween(String value1, String value2) {
            addCriterion("CFG_VALUE not between", value1, value2, "cfgValue");
            return (Criteria) this;
        }

        public Criteria andCfgDescIsNull() {
            addCriterion("CFG_DESC is null");
            return (Criteria) this;
        }

        public Criteria andCfgDescIsNotNull() {
            addCriterion("CFG_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andCfgDescEqualTo(String value) {
            addCriterion("CFG_DESC =", value, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescNotEqualTo(String value) {
            addCriterion("CFG_DESC <>", value, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescGreaterThan(String value) {
            addCriterion("CFG_DESC >", value, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescGreaterThanOrEqualTo(String value) {
            addCriterion("CFG_DESC >=", value, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescLessThan(String value) {
            addCriterion("CFG_DESC <", value, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescLessThanOrEqualTo(String value) {
            addCriterion("CFG_DESC <=", value, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescLike(String value) {
            addCriterion("CFG_DESC like", value, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescNotLike(String value) {
            addCriterion("CFG_DESC not like", value, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescIn(List<String> values) {
            addCriterion("CFG_DESC in", values, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescNotIn(List<String> values) {
            addCriterion("CFG_DESC not in", values, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescBetween(String value1, String value2) {
            addCriterion("CFG_DESC between", value1, value2, "cfgDesc");
            return (Criteria) this;
        }

        public Criteria andCfgDescNotBetween(String value1, String value2) {
            addCriterion("CFG_DESC not between", value1, value2, "cfgDesc");
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