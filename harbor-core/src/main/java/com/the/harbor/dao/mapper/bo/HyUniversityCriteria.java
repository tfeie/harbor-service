package com.the.harbor.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class HyUniversityCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyUniversityCriteria() {
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

        public Criteria andUniversityIdIsNull() {
            addCriterion("UNIVERSITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andUniversityIdIsNotNull() {
            addCriterion("UNIVERSITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUniversityIdEqualTo(String value) {
            addCriterion("UNIVERSITY_ID =", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdNotEqualTo(String value) {
            addCriterion("UNIVERSITY_ID <>", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdGreaterThan(String value) {
            addCriterion("UNIVERSITY_ID >", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdGreaterThanOrEqualTo(String value) {
            addCriterion("UNIVERSITY_ID >=", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdLessThan(String value) {
            addCriterion("UNIVERSITY_ID <", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdLessThanOrEqualTo(String value) {
            addCriterion("UNIVERSITY_ID <=", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdLike(String value) {
            addCriterion("UNIVERSITY_ID like", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdNotLike(String value) {
            addCriterion("UNIVERSITY_ID not like", value, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdIn(List<String> values) {
            addCriterion("UNIVERSITY_ID in", values, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdNotIn(List<String> values) {
            addCriterion("UNIVERSITY_ID not in", values, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdBetween(String value1, String value2) {
            addCriterion("UNIVERSITY_ID between", value1, value2, "universityId");
            return (Criteria) this;
        }

        public Criteria andUniversityIdNotBetween(String value1, String value2) {
            addCriterion("UNIVERSITY_ID not between", value1, value2, "universityId");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIsNull() {
            addCriterion("COUNTRY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIsNotNull() {
            addCriterion("COUNTRY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCountryCodeEqualTo(String value) {
            addCriterion("COUNTRY_CODE =", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotEqualTo(String value) {
            addCriterion("COUNTRY_CODE <>", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeGreaterThan(String value) {
            addCriterion("COUNTRY_CODE >", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("COUNTRY_CODE >=", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLessThan(String value) {
            addCriterion("COUNTRY_CODE <", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLessThanOrEqualTo(String value) {
            addCriterion("COUNTRY_CODE <=", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLike(String value) {
            addCriterion("COUNTRY_CODE like", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotLike(String value) {
            addCriterion("COUNTRY_CODE not like", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIn(List<String> values) {
            addCriterion("COUNTRY_CODE in", values, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotIn(List<String> values) {
            addCriterion("COUNTRY_CODE not in", values, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeBetween(String value1, String value2) {
            addCriterion("COUNTRY_CODE between", value1, value2, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotBetween(String value1, String value2) {
            addCriterion("COUNTRY_CODE not between", value1, value2, "countryCode");
            return (Criteria) this;
        }

        public Criteria andUniversityNameIsNull() {
            addCriterion("UNIVERSITY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUniversityNameIsNotNull() {
            addCriterion("UNIVERSITY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUniversityNameEqualTo(String value) {
            addCriterion("UNIVERSITY_NAME =", value, "universityName");
            return (Criteria) this;
        }

        public Criteria andUniversityNameNotEqualTo(String value) {
            addCriterion("UNIVERSITY_NAME <>", value, "universityName");
            return (Criteria) this;
        }

        public Criteria andUniversityNameGreaterThan(String value) {
            addCriterion("UNIVERSITY_NAME >", value, "universityName");
            return (Criteria) this;
        }

        public Criteria andUniversityNameGreaterThanOrEqualTo(String value) {
            addCriterion("UNIVERSITY_NAME >=", value, "universityName");
            return (Criteria) this;
        }

        public Criteria andUniversityNameLessThan(String value) {
            addCriterion("UNIVERSITY_NAME <", value, "universityName");
            return (Criteria) this;
        }

        public Criteria andUniversityNameLessThanOrEqualTo(String value) {
            addCriterion("UNIVERSITY_NAME <=", value, "universityName");
            return (Criteria) this;
        }

        public Criteria andUniversityNameLike(String value) {
            addCriterion("UNIVERSITY_NAME like", value, "universityName");
            return (Criteria) this;
        }

        public Criteria andUniversityNameNotLike(String value) {
            addCriterion("UNIVERSITY_NAME not like", value, "universityName");
            return (Criteria) this;
        }

        public Criteria andUniversityNameIn(List<String> values) {
            addCriterion("UNIVERSITY_NAME in", values, "universityName");
            return (Criteria) this;
        }

        public Criteria andUniversityNameNotIn(List<String> values) {
            addCriterion("UNIVERSITY_NAME not in", values, "universityName");
            return (Criteria) this;
        }

        public Criteria andUniversityNameBetween(String value1, String value2) {
            addCriterion("UNIVERSITY_NAME between", value1, value2, "universityName");
            return (Criteria) this;
        }

        public Criteria andUniversityNameNotBetween(String value1, String value2) {
            addCriterion("UNIVERSITY_NAME not between", value1, value2, "universityName");
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