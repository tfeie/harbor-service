package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HyUserCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyUserCriteria() {
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

        public Criteria andUserTypeIsNull() {
            addCriterion("USER_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("USER_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(String value) {
            addCriterion("USER_TYPE =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(String value) {
            addCriterion("USER_TYPE <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(String value) {
            addCriterion("USER_TYPE >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(String value) {
            addCriterion("USER_TYPE >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(String value) {
            addCriterion("USER_TYPE <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(String value) {
            addCriterion("USER_TYPE <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLike(String value) {
            addCriterion("USER_TYPE like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotLike(String value) {
            addCriterion("USER_TYPE not like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<String> values) {
            addCriterion("USER_TYPE in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<String> values) {
            addCriterion("USER_TYPE not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(String value1, String value2) {
            addCriterion("USER_TYPE between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(String value1, String value2) {
            addCriterion("USER_TYPE not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andHyIdIsNull() {
            addCriterion("HY_ID is null");
            return (Criteria) this;
        }

        public Criteria andHyIdIsNotNull() {
            addCriterion("HY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andHyIdEqualTo(String value) {
            addCriterion("HY_ID =", value, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdNotEqualTo(String value) {
            addCriterion("HY_ID <>", value, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdGreaterThan(String value) {
            addCriterion("HY_ID >", value, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdGreaterThanOrEqualTo(String value) {
            addCriterion("HY_ID >=", value, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdLessThan(String value) {
            addCriterion("HY_ID <", value, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdLessThanOrEqualTo(String value) {
            addCriterion("HY_ID <=", value, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdLike(String value) {
            addCriterion("HY_ID like", value, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdNotLike(String value) {
            addCriterion("HY_ID not like", value, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdIn(List<String> values) {
            addCriterion("HY_ID in", values, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdNotIn(List<String> values) {
            addCriterion("HY_ID not in", values, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdBetween(String value1, String value2) {
            addCriterion("HY_ID between", value1, value2, "hyId");
            return (Criteria) this;
        }

        public Criteria andHyIdNotBetween(String value1, String value2) {
            addCriterion("HY_ID not between", value1, value2, "hyId");
            return (Criteria) this;
        }

        public Criteria andEnNameIsNull() {
            addCriterion("EN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEnNameIsNotNull() {
            addCriterion("EN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEnNameEqualTo(String value) {
            addCriterion("EN_NAME =", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotEqualTo(String value) {
            addCriterion("EN_NAME <>", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameGreaterThan(String value) {
            addCriterion("EN_NAME >", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameGreaterThanOrEqualTo(String value) {
            addCriterion("EN_NAME >=", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameLessThan(String value) {
            addCriterion("EN_NAME <", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameLessThanOrEqualTo(String value) {
            addCriterion("EN_NAME <=", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameLike(String value) {
            addCriterion("EN_NAME like", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotLike(String value) {
            addCriterion("EN_NAME not like", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameIn(List<String> values) {
            addCriterion("EN_NAME in", values, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotIn(List<String> values) {
            addCriterion("EN_NAME not in", values, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameBetween(String value1, String value2) {
            addCriterion("EN_NAME between", value1, value2, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotBetween(String value1, String value2) {
            addCriterion("EN_NAME not between", value1, value2, "enName");
            return (Criteria) this;
        }

        public Criteria andChNameIsNull() {
            addCriterion("CH_NAME is null");
            return (Criteria) this;
        }

        public Criteria andChNameIsNotNull() {
            addCriterion("CH_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andChNameEqualTo(String value) {
            addCriterion("CH_NAME =", value, "chName");
            return (Criteria) this;
        }

        public Criteria andChNameNotEqualTo(String value) {
            addCriterion("CH_NAME <>", value, "chName");
            return (Criteria) this;
        }

        public Criteria andChNameGreaterThan(String value) {
            addCriterion("CH_NAME >", value, "chName");
            return (Criteria) this;
        }

        public Criteria andChNameGreaterThanOrEqualTo(String value) {
            addCriterion("CH_NAME >=", value, "chName");
            return (Criteria) this;
        }

        public Criteria andChNameLessThan(String value) {
            addCriterion("CH_NAME <", value, "chName");
            return (Criteria) this;
        }

        public Criteria andChNameLessThanOrEqualTo(String value) {
            addCriterion("CH_NAME <=", value, "chName");
            return (Criteria) this;
        }

        public Criteria andChNameLike(String value) {
            addCriterion("CH_NAME like", value, "chName");
            return (Criteria) this;
        }

        public Criteria andChNameNotLike(String value) {
            addCriterion("CH_NAME not like", value, "chName");
            return (Criteria) this;
        }

        public Criteria andChNameIn(List<String> values) {
            addCriterion("CH_NAME in", values, "chName");
            return (Criteria) this;
        }

        public Criteria andChNameNotIn(List<String> values) {
            addCriterion("CH_NAME not in", values, "chName");
            return (Criteria) this;
        }

        public Criteria andChNameBetween(String value1, String value2) {
            addCriterion("CH_NAME between", value1, value2, "chName");
            return (Criteria) this;
        }

        public Criteria andChNameNotBetween(String value1, String value2) {
            addCriterion("CH_NAME not between", value1, value2, "chName");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("SEX is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("SEX is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("SEX =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("SEX <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("SEX >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("SEX >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("SEX <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("SEX <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("SEX like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("SEX not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("SEX in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("SEX not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("SEX between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("SEX not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andWxOpenidIsNull() {
            addCriterion("WX_OPENID is null");
            return (Criteria) this;
        }

        public Criteria andWxOpenidIsNotNull() {
            addCriterion("WX_OPENID is not null");
            return (Criteria) this;
        }

        public Criteria andWxOpenidEqualTo(String value) {
            addCriterion("WX_OPENID =", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidNotEqualTo(String value) {
            addCriterion("WX_OPENID <>", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidGreaterThan(String value) {
            addCriterion("WX_OPENID >", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("WX_OPENID >=", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidLessThan(String value) {
            addCriterion("WX_OPENID <", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidLessThanOrEqualTo(String value) {
            addCriterion("WX_OPENID <=", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidLike(String value) {
            addCriterion("WX_OPENID like", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidNotLike(String value) {
            addCriterion("WX_OPENID not like", value, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidIn(List<String> values) {
            addCriterion("WX_OPENID in", values, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidNotIn(List<String> values) {
            addCriterion("WX_OPENID not in", values, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidBetween(String value1, String value2) {
            addCriterion("WX_OPENID between", value1, value2, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxOpenidNotBetween(String value1, String value2) {
            addCriterion("WX_OPENID not between", value1, value2, "wxOpenid");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgIsNull() {
            addCriterion("WX_HEADIMG is null");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgIsNotNull() {
            addCriterion("WX_HEADIMG is not null");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgEqualTo(String value) {
            addCriterion("WX_HEADIMG =", value, "wxHeadimg");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgNotEqualTo(String value) {
            addCriterion("WX_HEADIMG <>", value, "wxHeadimg");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgGreaterThan(String value) {
            addCriterion("WX_HEADIMG >", value, "wxHeadimg");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgGreaterThanOrEqualTo(String value) {
            addCriterion("WX_HEADIMG >=", value, "wxHeadimg");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgLessThan(String value) {
            addCriterion("WX_HEADIMG <", value, "wxHeadimg");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgLessThanOrEqualTo(String value) {
            addCriterion("WX_HEADIMG <=", value, "wxHeadimg");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgLike(String value) {
            addCriterion("WX_HEADIMG like", value, "wxHeadimg");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgNotLike(String value) {
            addCriterion("WX_HEADIMG not like", value, "wxHeadimg");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgIn(List<String> values) {
            addCriterion("WX_HEADIMG in", values, "wxHeadimg");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgNotIn(List<String> values) {
            addCriterion("WX_HEADIMG not in", values, "wxHeadimg");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgBetween(String value1, String value2) {
            addCriterion("WX_HEADIMG between", value1, value2, "wxHeadimg");
            return (Criteria) this;
        }

        public Criteria andWxHeadimgNotBetween(String value1, String value2) {
            addCriterion("WX_HEADIMG not between", value1, value2, "wxHeadimg");
            return (Criteria) this;
        }

        public Criteria andAbroadCountryIsNull() {
            addCriterion("ABROAD_COUNTRY is null");
            return (Criteria) this;
        }

        public Criteria andAbroadCountryIsNotNull() {
            addCriterion("ABROAD_COUNTRY is not null");
            return (Criteria) this;
        }

        public Criteria andAbroadCountryEqualTo(String value) {
            addCriterion("ABROAD_COUNTRY =", value, "abroadCountry");
            return (Criteria) this;
        }

        public Criteria andAbroadCountryNotEqualTo(String value) {
            addCriterion("ABROAD_COUNTRY <>", value, "abroadCountry");
            return (Criteria) this;
        }

        public Criteria andAbroadCountryGreaterThan(String value) {
            addCriterion("ABROAD_COUNTRY >", value, "abroadCountry");
            return (Criteria) this;
        }

        public Criteria andAbroadCountryGreaterThanOrEqualTo(String value) {
            addCriterion("ABROAD_COUNTRY >=", value, "abroadCountry");
            return (Criteria) this;
        }

        public Criteria andAbroadCountryLessThan(String value) {
            addCriterion("ABROAD_COUNTRY <", value, "abroadCountry");
            return (Criteria) this;
        }

        public Criteria andAbroadCountryLessThanOrEqualTo(String value) {
            addCriterion("ABROAD_COUNTRY <=", value, "abroadCountry");
            return (Criteria) this;
        }

        public Criteria andAbroadCountryLike(String value) {
            addCriterion("ABROAD_COUNTRY like", value, "abroadCountry");
            return (Criteria) this;
        }

        public Criteria andAbroadCountryNotLike(String value) {
            addCriterion("ABROAD_COUNTRY not like", value, "abroadCountry");
            return (Criteria) this;
        }

        public Criteria andAbroadCountryIn(List<String> values) {
            addCriterion("ABROAD_COUNTRY in", values, "abroadCountry");
            return (Criteria) this;
        }

        public Criteria andAbroadCountryNotIn(List<String> values) {
            addCriterion("ABROAD_COUNTRY not in", values, "abroadCountry");
            return (Criteria) this;
        }

        public Criteria andAbroadCountryBetween(String value1, String value2) {
            addCriterion("ABROAD_COUNTRY between", value1, value2, "abroadCountry");
            return (Criteria) this;
        }

        public Criteria andAbroadCountryNotBetween(String value1, String value2) {
            addCriterion("ABROAD_COUNTRY not between", value1, value2, "abroadCountry");
            return (Criteria) this;
        }

        public Criteria andAbroadUniversityIsNull() {
            addCriterion("ABROAD_UNIVERSITY is null");
            return (Criteria) this;
        }

        public Criteria andAbroadUniversityIsNotNull() {
            addCriterion("ABROAD_UNIVERSITY is not null");
            return (Criteria) this;
        }

        public Criteria andAbroadUniversityEqualTo(String value) {
            addCriterion("ABROAD_UNIVERSITY =", value, "abroadUniversity");
            return (Criteria) this;
        }

        public Criteria andAbroadUniversityNotEqualTo(String value) {
            addCriterion("ABROAD_UNIVERSITY <>", value, "abroadUniversity");
            return (Criteria) this;
        }

        public Criteria andAbroadUniversityGreaterThan(String value) {
            addCriterion("ABROAD_UNIVERSITY >", value, "abroadUniversity");
            return (Criteria) this;
        }

        public Criteria andAbroadUniversityGreaterThanOrEqualTo(String value) {
            addCriterion("ABROAD_UNIVERSITY >=", value, "abroadUniversity");
            return (Criteria) this;
        }

        public Criteria andAbroadUniversityLessThan(String value) {
            addCriterion("ABROAD_UNIVERSITY <", value, "abroadUniversity");
            return (Criteria) this;
        }

        public Criteria andAbroadUniversityLessThanOrEqualTo(String value) {
            addCriterion("ABROAD_UNIVERSITY <=", value, "abroadUniversity");
            return (Criteria) this;
        }

        public Criteria andAbroadUniversityLike(String value) {
            addCriterion("ABROAD_UNIVERSITY like", value, "abroadUniversity");
            return (Criteria) this;
        }

        public Criteria andAbroadUniversityNotLike(String value) {
            addCriterion("ABROAD_UNIVERSITY not like", value, "abroadUniversity");
            return (Criteria) this;
        }

        public Criteria andAbroadUniversityIn(List<String> values) {
            addCriterion("ABROAD_UNIVERSITY in", values, "abroadUniversity");
            return (Criteria) this;
        }

        public Criteria andAbroadUniversityNotIn(List<String> values) {
            addCriterion("ABROAD_UNIVERSITY not in", values, "abroadUniversity");
            return (Criteria) this;
        }

        public Criteria andAbroadUniversityBetween(String value1, String value2) {
            addCriterion("ABROAD_UNIVERSITY between", value1, value2, "abroadUniversity");
            return (Criteria) this;
        }

        public Criteria andAbroadUniversityNotBetween(String value1, String value2) {
            addCriterion("ABROAD_UNIVERSITY not between", value1, value2, "abroadUniversity");
            return (Criteria) this;
        }

        public Criteria andAtCountryIsNull() {
            addCriterion("AT_COUNTRY is null");
            return (Criteria) this;
        }

        public Criteria andAtCountryIsNotNull() {
            addCriterion("AT_COUNTRY is not null");
            return (Criteria) this;
        }

        public Criteria andAtCountryEqualTo(String value) {
            addCriterion("AT_COUNTRY =", value, "atCountry");
            return (Criteria) this;
        }

        public Criteria andAtCountryNotEqualTo(String value) {
            addCriterion("AT_COUNTRY <>", value, "atCountry");
            return (Criteria) this;
        }

        public Criteria andAtCountryGreaterThan(String value) {
            addCriterion("AT_COUNTRY >", value, "atCountry");
            return (Criteria) this;
        }

        public Criteria andAtCountryGreaterThanOrEqualTo(String value) {
            addCriterion("AT_COUNTRY >=", value, "atCountry");
            return (Criteria) this;
        }

        public Criteria andAtCountryLessThan(String value) {
            addCriterion("AT_COUNTRY <", value, "atCountry");
            return (Criteria) this;
        }

        public Criteria andAtCountryLessThanOrEqualTo(String value) {
            addCriterion("AT_COUNTRY <=", value, "atCountry");
            return (Criteria) this;
        }

        public Criteria andAtCountryLike(String value) {
            addCriterion("AT_COUNTRY like", value, "atCountry");
            return (Criteria) this;
        }

        public Criteria andAtCountryNotLike(String value) {
            addCriterion("AT_COUNTRY not like", value, "atCountry");
            return (Criteria) this;
        }

        public Criteria andAtCountryIn(List<String> values) {
            addCriterion("AT_COUNTRY in", values, "atCountry");
            return (Criteria) this;
        }

        public Criteria andAtCountryNotIn(List<String> values) {
            addCriterion("AT_COUNTRY not in", values, "atCountry");
            return (Criteria) this;
        }

        public Criteria andAtCountryBetween(String value1, String value2) {
            addCriterion("AT_COUNTRY between", value1, value2, "atCountry");
            return (Criteria) this;
        }

        public Criteria andAtCountryNotBetween(String value1, String value2) {
            addCriterion("AT_COUNTRY not between", value1, value2, "atCountry");
            return (Criteria) this;
        }

        public Criteria andAtProvinceIsNull() {
            addCriterion("AT_PROVINCE is null");
            return (Criteria) this;
        }

        public Criteria andAtProvinceIsNotNull() {
            addCriterion("AT_PROVINCE is not null");
            return (Criteria) this;
        }

        public Criteria andAtProvinceEqualTo(String value) {
            addCriterion("AT_PROVINCE =", value, "atProvince");
            return (Criteria) this;
        }

        public Criteria andAtProvinceNotEqualTo(String value) {
            addCriterion("AT_PROVINCE <>", value, "atProvince");
            return (Criteria) this;
        }

        public Criteria andAtProvinceGreaterThan(String value) {
            addCriterion("AT_PROVINCE >", value, "atProvince");
            return (Criteria) this;
        }

        public Criteria andAtProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("AT_PROVINCE >=", value, "atProvince");
            return (Criteria) this;
        }

        public Criteria andAtProvinceLessThan(String value) {
            addCriterion("AT_PROVINCE <", value, "atProvince");
            return (Criteria) this;
        }

        public Criteria andAtProvinceLessThanOrEqualTo(String value) {
            addCriterion("AT_PROVINCE <=", value, "atProvince");
            return (Criteria) this;
        }

        public Criteria andAtProvinceLike(String value) {
            addCriterion("AT_PROVINCE like", value, "atProvince");
            return (Criteria) this;
        }

        public Criteria andAtProvinceNotLike(String value) {
            addCriterion("AT_PROVINCE not like", value, "atProvince");
            return (Criteria) this;
        }

        public Criteria andAtProvinceIn(List<String> values) {
            addCriterion("AT_PROVINCE in", values, "atProvince");
            return (Criteria) this;
        }

        public Criteria andAtProvinceNotIn(List<String> values) {
            addCriterion("AT_PROVINCE not in", values, "atProvince");
            return (Criteria) this;
        }

        public Criteria andAtProvinceBetween(String value1, String value2) {
            addCriterion("AT_PROVINCE between", value1, value2, "atProvince");
            return (Criteria) this;
        }

        public Criteria andAtProvinceNotBetween(String value1, String value2) {
            addCriterion("AT_PROVINCE not between", value1, value2, "atProvince");
            return (Criteria) this;
        }

        public Criteria andAtCityIsNull() {
            addCriterion("AT_CITY is null");
            return (Criteria) this;
        }

        public Criteria andAtCityIsNotNull() {
            addCriterion("AT_CITY is not null");
            return (Criteria) this;
        }

        public Criteria andAtCityEqualTo(String value) {
            addCriterion("AT_CITY =", value, "atCity");
            return (Criteria) this;
        }

        public Criteria andAtCityNotEqualTo(String value) {
            addCriterion("AT_CITY <>", value, "atCity");
            return (Criteria) this;
        }

        public Criteria andAtCityGreaterThan(String value) {
            addCriterion("AT_CITY >", value, "atCity");
            return (Criteria) this;
        }

        public Criteria andAtCityGreaterThanOrEqualTo(String value) {
            addCriterion("AT_CITY >=", value, "atCity");
            return (Criteria) this;
        }

        public Criteria andAtCityLessThan(String value) {
            addCriterion("AT_CITY <", value, "atCity");
            return (Criteria) this;
        }

        public Criteria andAtCityLessThanOrEqualTo(String value) {
            addCriterion("AT_CITY <=", value, "atCity");
            return (Criteria) this;
        }

        public Criteria andAtCityLike(String value) {
            addCriterion("AT_CITY like", value, "atCity");
            return (Criteria) this;
        }

        public Criteria andAtCityNotLike(String value) {
            addCriterion("AT_CITY not like", value, "atCity");
            return (Criteria) this;
        }

        public Criteria andAtCityIn(List<String> values) {
            addCriterion("AT_CITY in", values, "atCity");
            return (Criteria) this;
        }

        public Criteria andAtCityNotIn(List<String> values) {
            addCriterion("AT_CITY not in", values, "atCity");
            return (Criteria) this;
        }

        public Criteria andAtCityBetween(String value1, String value2) {
            addCriterion("AT_CITY between", value1, value2, "atCity");
            return (Criteria) this;
        }

        public Criteria andAtCityNotBetween(String value1, String value2) {
            addCriterion("AT_CITY not between", value1, value2, "atCity");
            return (Criteria) this;
        }

        public Criteria andSignatureIsNull() {
            addCriterion("SIGNATURE is null");
            return (Criteria) this;
        }

        public Criteria andSignatureIsNotNull() {
            addCriterion("SIGNATURE is not null");
            return (Criteria) this;
        }

        public Criteria andSignatureEqualTo(String value) {
            addCriterion("SIGNATURE =", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureNotEqualTo(String value) {
            addCriterion("SIGNATURE <>", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureGreaterThan(String value) {
            addCriterion("SIGNATURE >", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureGreaterThanOrEqualTo(String value) {
            addCriterion("SIGNATURE >=", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureLessThan(String value) {
            addCriterion("SIGNATURE <", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureLessThanOrEqualTo(String value) {
            addCriterion("SIGNATURE <=", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureLike(String value) {
            addCriterion("SIGNATURE like", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureNotLike(String value) {
            addCriterion("SIGNATURE not like", value, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureIn(List<String> values) {
            addCriterion("SIGNATURE in", values, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureNotIn(List<String> values) {
            addCriterion("SIGNATURE not in", values, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureBetween(String value1, String value2) {
            addCriterion("SIGNATURE between", value1, value2, "signature");
            return (Criteria) this;
        }

        public Criteria andSignatureNotBetween(String value1, String value2) {
            addCriterion("SIGNATURE not between", value1, value2, "signature");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNull() {
            addCriterion("MOBILE_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIsNotNull() {
            addCriterion("MOBILE_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneEqualTo(String value) {
            addCriterion("MOBILE_PHONE =", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotEqualTo(String value) {
            addCriterion("MOBILE_PHONE <>", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThan(String value) {
            addCriterion("MOBILE_PHONE >", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILE_PHONE >=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThan(String value) {
            addCriterion("MOBILE_PHONE <", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLessThanOrEqualTo(String value) {
            addCriterion("MOBILE_PHONE <=", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneLike(String value) {
            addCriterion("MOBILE_PHONE like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotLike(String value) {
            addCriterion("MOBILE_PHONE not like", value, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneIn(List<String> values) {
            addCriterion("MOBILE_PHONE in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotIn(List<String> values) {
            addCriterion("MOBILE_PHONE not in", values, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneBetween(String value1, String value2) {
            addCriterion("MOBILE_PHONE between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andMobilePhoneNotBetween(String value1, String value2) {
            addCriterion("MOBILE_PHONE not between", value1, value2, "mobilePhone");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusIsNull() {
            addCriterion("MARITAL_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusIsNotNull() {
            addCriterion("MARITAL_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusEqualTo(String value) {
            addCriterion("MARITAL_STATUS =", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusNotEqualTo(String value) {
            addCriterion("MARITAL_STATUS <>", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusGreaterThan(String value) {
            addCriterion("MARITAL_STATUS >", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusGreaterThanOrEqualTo(String value) {
            addCriterion("MARITAL_STATUS >=", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusLessThan(String value) {
            addCriterion("MARITAL_STATUS <", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusLessThanOrEqualTo(String value) {
            addCriterion("MARITAL_STATUS <=", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusLike(String value) {
            addCriterion("MARITAL_STATUS like", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusNotLike(String value) {
            addCriterion("MARITAL_STATUS not like", value, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusIn(List<String> values) {
            addCriterion("MARITAL_STATUS in", values, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusNotIn(List<String> values) {
            addCriterion("MARITAL_STATUS not in", values, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusBetween(String value1, String value2) {
            addCriterion("MARITAL_STATUS between", value1, value2, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andMaritalStatusNotBetween(String value1, String value2) {
            addCriterion("MARITAL_STATUS not between", value1, value2, "maritalStatus");
            return (Criteria) this;
        }

        public Criteria andConstellationIsNull() {
            addCriterion("CONSTELLATION is null");
            return (Criteria) this;
        }

        public Criteria andConstellationIsNotNull() {
            addCriterion("CONSTELLATION is not null");
            return (Criteria) this;
        }

        public Criteria andConstellationEqualTo(String value) {
            addCriterion("CONSTELLATION =", value, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationNotEqualTo(String value) {
            addCriterion("CONSTELLATION <>", value, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationGreaterThan(String value) {
            addCriterion("CONSTELLATION >", value, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationGreaterThanOrEqualTo(String value) {
            addCriterion("CONSTELLATION >=", value, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationLessThan(String value) {
            addCriterion("CONSTELLATION <", value, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationLessThanOrEqualTo(String value) {
            addCriterion("CONSTELLATION <=", value, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationLike(String value) {
            addCriterion("CONSTELLATION like", value, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationNotLike(String value) {
            addCriterion("CONSTELLATION not like", value, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationIn(List<String> values) {
            addCriterion("CONSTELLATION in", values, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationNotIn(List<String> values) {
            addCriterion("CONSTELLATION not in", values, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationBetween(String value1, String value2) {
            addCriterion("CONSTELLATION between", value1, value2, "constellation");
            return (Criteria) this;
        }

        public Criteria andConstellationNotBetween(String value1, String value2) {
            addCriterion("CONSTELLATION not between", value1, value2, "constellation");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNull() {
            addCriterion("COMPANY is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("COMPANY is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("COMPANY =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("COMPANY <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("COMPANY >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("COMPANY <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("COMPANY <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("COMPANY like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("COMPANY not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("COMPANY in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("COMPANY not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("COMPANY between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("COMPANY not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andIndustryIsNull() {
            addCriterion("INDUSTRY is null");
            return (Criteria) this;
        }

        public Criteria andIndustryIsNotNull() {
            addCriterion("INDUSTRY is not null");
            return (Criteria) this;
        }

        public Criteria andIndustryEqualTo(String value) {
            addCriterion("INDUSTRY =", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotEqualTo(String value) {
            addCriterion("INDUSTRY <>", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryGreaterThan(String value) {
            addCriterion("INDUSTRY >", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryGreaterThanOrEqualTo(String value) {
            addCriterion("INDUSTRY >=", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLessThan(String value) {
            addCriterion("INDUSTRY <", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLessThanOrEqualTo(String value) {
            addCriterion("INDUSTRY <=", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryLike(String value) {
            addCriterion("INDUSTRY like", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotLike(String value) {
            addCriterion("INDUSTRY not like", value, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryIn(List<String> values) {
            addCriterion("INDUSTRY in", values, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotIn(List<String> values) {
            addCriterion("INDUSTRY not in", values, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryBetween(String value1, String value2) {
            addCriterion("INDUSTRY between", value1, value2, "industry");
            return (Criteria) this;
        }

        public Criteria andIndustryNotBetween(String value1, String value2) {
            addCriterion("INDUSTRY not between", value1, value2, "industry");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andRegDateIsNull() {
            addCriterion("REG_DATE is null");
            return (Criteria) this;
        }

        public Criteria andRegDateIsNotNull() {
            addCriterion("REG_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andRegDateEqualTo(Timestamp value) {
            addCriterion("REG_DATE =", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateNotEqualTo(Timestamp value) {
            addCriterion("REG_DATE <>", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateGreaterThan(Timestamp value) {
            addCriterion("REG_DATE >", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("REG_DATE >=", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateLessThan(Timestamp value) {
            addCriterion("REG_DATE <", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("REG_DATE <=", value, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateIn(List<Timestamp> values) {
            addCriterion("REG_DATE in", values, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateNotIn(List<Timestamp> values) {
            addCriterion("REG_DATE not in", values, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("REG_DATE between", value1, value2, "regDate");
            return (Criteria) this;
        }

        public Criteria andRegDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("REG_DATE not between", value1, value2, "regDate");
            return (Criteria) this;
        }

        public Criteria andUserStatusIsNull() {
            addCriterion("USER_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andUserStatusIsNotNull() {
            addCriterion("USER_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andUserStatusEqualTo(String value) {
            addCriterion("USER_STATUS =", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotEqualTo(String value) {
            addCriterion("USER_STATUS <>", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusGreaterThan(String value) {
            addCriterion("USER_STATUS >", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusGreaterThanOrEqualTo(String value) {
            addCriterion("USER_STATUS >=", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLessThan(String value) {
            addCriterion("USER_STATUS <", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLessThanOrEqualTo(String value) {
            addCriterion("USER_STATUS <=", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLike(String value) {
            addCriterion("USER_STATUS like", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotLike(String value) {
            addCriterion("USER_STATUS not like", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusIn(List<String> values) {
            addCriterion("USER_STATUS in", values, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotIn(List<String> values) {
            addCriterion("USER_STATUS not in", values, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusBetween(String value1, String value2) {
            addCriterion("USER_STATUS between", value1, value2, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotBetween(String value1, String value2) {
            addCriterion("USER_STATUS not between", value1, value2, "userStatus");
            return (Criteria) this;
        }

        public Criteria andIdcardPhotoIsNull() {
            addCriterion("IDCARD_PHOTO is null");
            return (Criteria) this;
        }

        public Criteria andIdcardPhotoIsNotNull() {
            addCriterion("IDCARD_PHOTO is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardPhotoEqualTo(String value) {
            addCriterion("IDCARD_PHOTO =", value, "idcardPhoto");
            return (Criteria) this;
        }

        public Criteria andIdcardPhotoNotEqualTo(String value) {
            addCriterion("IDCARD_PHOTO <>", value, "idcardPhoto");
            return (Criteria) this;
        }

        public Criteria andIdcardPhotoGreaterThan(String value) {
            addCriterion("IDCARD_PHOTO >", value, "idcardPhoto");
            return (Criteria) this;
        }

        public Criteria andIdcardPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("IDCARD_PHOTO >=", value, "idcardPhoto");
            return (Criteria) this;
        }

        public Criteria andIdcardPhotoLessThan(String value) {
            addCriterion("IDCARD_PHOTO <", value, "idcardPhoto");
            return (Criteria) this;
        }

        public Criteria andIdcardPhotoLessThanOrEqualTo(String value) {
            addCriterion("IDCARD_PHOTO <=", value, "idcardPhoto");
            return (Criteria) this;
        }

        public Criteria andIdcardPhotoLike(String value) {
            addCriterion("IDCARD_PHOTO like", value, "idcardPhoto");
            return (Criteria) this;
        }

        public Criteria andIdcardPhotoNotLike(String value) {
            addCriterion("IDCARD_PHOTO not like", value, "idcardPhoto");
            return (Criteria) this;
        }

        public Criteria andIdcardPhotoIn(List<String> values) {
            addCriterion("IDCARD_PHOTO in", values, "idcardPhoto");
            return (Criteria) this;
        }

        public Criteria andIdcardPhotoNotIn(List<String> values) {
            addCriterion("IDCARD_PHOTO not in", values, "idcardPhoto");
            return (Criteria) this;
        }

        public Criteria andIdcardPhotoBetween(String value1, String value2) {
            addCriterion("IDCARD_PHOTO between", value1, value2, "idcardPhoto");
            return (Criteria) this;
        }

        public Criteria andIdcardPhotoNotBetween(String value1, String value2) {
            addCriterion("IDCARD_PHOTO not between", value1, value2, "idcardPhoto");
            return (Criteria) this;
        }

        public Criteria andOverseasPhotoIsNull() {
            addCriterion("OVERSEAS_PHOTO is null");
            return (Criteria) this;
        }

        public Criteria andOverseasPhotoIsNotNull() {
            addCriterion("OVERSEAS_PHOTO is not null");
            return (Criteria) this;
        }

        public Criteria andOverseasPhotoEqualTo(String value) {
            addCriterion("OVERSEAS_PHOTO =", value, "overseasPhoto");
            return (Criteria) this;
        }

        public Criteria andOverseasPhotoNotEqualTo(String value) {
            addCriterion("OVERSEAS_PHOTO <>", value, "overseasPhoto");
            return (Criteria) this;
        }

        public Criteria andOverseasPhotoGreaterThan(String value) {
            addCriterion("OVERSEAS_PHOTO >", value, "overseasPhoto");
            return (Criteria) this;
        }

        public Criteria andOverseasPhotoGreaterThanOrEqualTo(String value) {
            addCriterion("OVERSEAS_PHOTO >=", value, "overseasPhoto");
            return (Criteria) this;
        }

        public Criteria andOverseasPhotoLessThan(String value) {
            addCriterion("OVERSEAS_PHOTO <", value, "overseasPhoto");
            return (Criteria) this;
        }

        public Criteria andOverseasPhotoLessThanOrEqualTo(String value) {
            addCriterion("OVERSEAS_PHOTO <=", value, "overseasPhoto");
            return (Criteria) this;
        }

        public Criteria andOverseasPhotoLike(String value) {
            addCriterion("OVERSEAS_PHOTO like", value, "overseasPhoto");
            return (Criteria) this;
        }

        public Criteria andOverseasPhotoNotLike(String value) {
            addCriterion("OVERSEAS_PHOTO not like", value, "overseasPhoto");
            return (Criteria) this;
        }

        public Criteria andOverseasPhotoIn(List<String> values) {
            addCriterion("OVERSEAS_PHOTO in", values, "overseasPhoto");
            return (Criteria) this;
        }

        public Criteria andOverseasPhotoNotIn(List<String> values) {
            addCriterion("OVERSEAS_PHOTO not in", values, "overseasPhoto");
            return (Criteria) this;
        }

        public Criteria andOverseasPhotoBetween(String value1, String value2) {
            addCriterion("OVERSEAS_PHOTO between", value1, value2, "overseasPhoto");
            return (Criteria) this;
        }

        public Criteria andOverseasPhotoNotBetween(String value1, String value2) {
            addCriterion("OVERSEAS_PHOTO not between", value1, value2, "overseasPhoto");
            return (Criteria) this;
        }

        public Criteria andSubmitCertDateIsNull() {
            addCriterion("SUBMIT_CERT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andSubmitCertDateIsNotNull() {
            addCriterion("SUBMIT_CERT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitCertDateEqualTo(Timestamp value) {
            addCriterion("SUBMIT_CERT_DATE =", value, "submitCertDate");
            return (Criteria) this;
        }

        public Criteria andSubmitCertDateNotEqualTo(Timestamp value) {
            addCriterion("SUBMIT_CERT_DATE <>", value, "submitCertDate");
            return (Criteria) this;
        }

        public Criteria andSubmitCertDateGreaterThan(Timestamp value) {
            addCriterion("SUBMIT_CERT_DATE >", value, "submitCertDate");
            return (Criteria) this;
        }

        public Criteria andSubmitCertDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("SUBMIT_CERT_DATE >=", value, "submitCertDate");
            return (Criteria) this;
        }

        public Criteria andSubmitCertDateLessThan(Timestamp value) {
            addCriterion("SUBMIT_CERT_DATE <", value, "submitCertDate");
            return (Criteria) this;
        }

        public Criteria andSubmitCertDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("SUBMIT_CERT_DATE <=", value, "submitCertDate");
            return (Criteria) this;
        }

        public Criteria andSubmitCertDateIn(List<Timestamp> values) {
            addCriterion("SUBMIT_CERT_DATE in", values, "submitCertDate");
            return (Criteria) this;
        }

        public Criteria andSubmitCertDateNotIn(List<Timestamp> values) {
            addCriterion("SUBMIT_CERT_DATE not in", values, "submitCertDate");
            return (Criteria) this;
        }

        public Criteria andSubmitCertDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SUBMIT_CERT_DATE between", value1, value2, "submitCertDate");
            return (Criteria) this;
        }

        public Criteria andSubmitCertDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("SUBMIT_CERT_DATE not between", value1, value2, "submitCertDate");
            return (Criteria) this;
        }

        public Criteria andCertificationDateIsNull() {
            addCriterion("CERTIFICATION_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCertificationDateIsNotNull() {
            addCriterion("CERTIFICATION_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCertificationDateEqualTo(Timestamp value) {
            addCriterion("CERTIFICATION_DATE =", value, "certificationDate");
            return (Criteria) this;
        }

        public Criteria andCertificationDateNotEqualTo(Timestamp value) {
            addCriterion("CERTIFICATION_DATE <>", value, "certificationDate");
            return (Criteria) this;
        }

        public Criteria andCertificationDateGreaterThan(Timestamp value) {
            addCriterion("CERTIFICATION_DATE >", value, "certificationDate");
            return (Criteria) this;
        }

        public Criteria andCertificationDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CERTIFICATION_DATE >=", value, "certificationDate");
            return (Criteria) this;
        }

        public Criteria andCertificationDateLessThan(Timestamp value) {
            addCriterion("CERTIFICATION_DATE <", value, "certificationDate");
            return (Criteria) this;
        }

        public Criteria andCertificationDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("CERTIFICATION_DATE <=", value, "certificationDate");
            return (Criteria) this;
        }

        public Criteria andCertificationDateIn(List<Timestamp> values) {
            addCriterion("CERTIFICATION_DATE in", values, "certificationDate");
            return (Criteria) this;
        }

        public Criteria andCertificationDateNotIn(List<Timestamp> values) {
            addCriterion("CERTIFICATION_DATE not in", values, "certificationDate");
            return (Criteria) this;
        }

        public Criteria andCertificationDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CERTIFICATION_DATE between", value1, value2, "certificationDate");
            return (Criteria) this;
        }

        public Criteria andCertificationDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CERTIFICATION_DATE not between", value1, value2, "certificationDate");
            return (Criteria) this;
        }

        public Criteria andAccessPermissionIsNull() {
            addCriterion("ACCESS_PERMISSION is null");
            return (Criteria) this;
        }

        public Criteria andAccessPermissionIsNotNull() {
            addCriterion("ACCESS_PERMISSION is not null");
            return (Criteria) this;
        }

        public Criteria andAccessPermissionEqualTo(String value) {
            addCriterion("ACCESS_PERMISSION =", value, "accessPermission");
            return (Criteria) this;
        }

        public Criteria andAccessPermissionNotEqualTo(String value) {
            addCriterion("ACCESS_PERMISSION <>", value, "accessPermission");
            return (Criteria) this;
        }

        public Criteria andAccessPermissionGreaterThan(String value) {
            addCriterion("ACCESS_PERMISSION >", value, "accessPermission");
            return (Criteria) this;
        }

        public Criteria andAccessPermissionGreaterThanOrEqualTo(String value) {
            addCriterion("ACCESS_PERMISSION >=", value, "accessPermission");
            return (Criteria) this;
        }

        public Criteria andAccessPermissionLessThan(String value) {
            addCriterion("ACCESS_PERMISSION <", value, "accessPermission");
            return (Criteria) this;
        }

        public Criteria andAccessPermissionLessThanOrEqualTo(String value) {
            addCriterion("ACCESS_PERMISSION <=", value, "accessPermission");
            return (Criteria) this;
        }

        public Criteria andAccessPermissionLike(String value) {
            addCriterion("ACCESS_PERMISSION like", value, "accessPermission");
            return (Criteria) this;
        }

        public Criteria andAccessPermissionNotLike(String value) {
            addCriterion("ACCESS_PERMISSION not like", value, "accessPermission");
            return (Criteria) this;
        }

        public Criteria andAccessPermissionIn(List<String> values) {
            addCriterion("ACCESS_PERMISSION in", values, "accessPermission");
            return (Criteria) this;
        }

        public Criteria andAccessPermissionNotIn(List<String> values) {
            addCriterion("ACCESS_PERMISSION not in", values, "accessPermission");
            return (Criteria) this;
        }

        public Criteria andAccessPermissionBetween(String value1, String value2) {
            addCriterion("ACCESS_PERMISSION between", value1, value2, "accessPermission");
            return (Criteria) this;
        }

        public Criteria andAccessPermissionNotBetween(String value1, String value2) {
            addCriterion("ACCESS_PERMISSION not between", value1, value2, "accessPermission");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Timestamp value) {
            addCriterion("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Timestamp value) {
            addCriterion("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Timestamp value) {
            addCriterion("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Timestamp value) {
            addCriterion("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Timestamp> values) {
            addCriterion("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Timestamp> values) {
            addCriterion("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("UPDATE_DATE not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIsNull() {
            addCriterion("MEMBER_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIsNotNull() {
            addCriterion("MEMBER_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andMemberLevelEqualTo(String value) {
            addCriterion("MEMBER_LEVEL =", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNotEqualTo(String value) {
            addCriterion("MEMBER_LEVEL <>", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelGreaterThan(String value) {
            addCriterion("MEMBER_LEVEL >", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelGreaterThanOrEqualTo(String value) {
            addCriterion("MEMBER_LEVEL >=", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelLessThan(String value) {
            addCriterion("MEMBER_LEVEL <", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelLessThanOrEqualTo(String value) {
            addCriterion("MEMBER_LEVEL <=", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelLike(String value) {
            addCriterion("MEMBER_LEVEL like", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNotLike(String value) {
            addCriterion("MEMBER_LEVEL not like", value, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelIn(List<String> values) {
            addCriterion("MEMBER_LEVEL in", values, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNotIn(List<String> values) {
            addCriterion("MEMBER_LEVEL not in", values, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelBetween(String value1, String value2) {
            addCriterion("MEMBER_LEVEL between", value1, value2, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andMemberLevelNotBetween(String value1, String value2) {
            addCriterion("MEMBER_LEVEL not between", value1, value2, "memberLevel");
            return (Criteria) this;
        }

        public Criteria andEffDateIsNull() {
            addCriterion("EFF_DATE is null");
            return (Criteria) this;
        }

        public Criteria andEffDateIsNotNull() {
            addCriterion("EFF_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andEffDateEqualTo(Timestamp value) {
            addCriterion("EFF_DATE =", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateNotEqualTo(Timestamp value) {
            addCriterion("EFF_DATE <>", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateGreaterThan(Timestamp value) {
            addCriterion("EFF_DATE >", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("EFF_DATE >=", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateLessThan(Timestamp value) {
            addCriterion("EFF_DATE <", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("EFF_DATE <=", value, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateIn(List<Timestamp> values) {
            addCriterion("EFF_DATE in", values, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateNotIn(List<Timestamp> values) {
            addCriterion("EFF_DATE not in", values, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("EFF_DATE between", value1, value2, "effDate");
            return (Criteria) this;
        }

        public Criteria andEffDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("EFF_DATE not between", value1, value2, "effDate");
            return (Criteria) this;
        }

        public Criteria andExpDateIsNull() {
            addCriterion("EXP_DATE is null");
            return (Criteria) this;
        }

        public Criteria andExpDateIsNotNull() {
            addCriterion("EXP_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andExpDateEqualTo(Timestamp value) {
            addCriterion("EXP_DATE =", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateNotEqualTo(Timestamp value) {
            addCriterion("EXP_DATE <>", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateGreaterThan(Timestamp value) {
            addCriterion("EXP_DATE >", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("EXP_DATE >=", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateLessThan(Timestamp value) {
            addCriterion("EXP_DATE <", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("EXP_DATE <=", value, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateIn(List<Timestamp> values) {
            addCriterion("EXP_DATE in", values, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateNotIn(List<Timestamp> values) {
            addCriterion("EXP_DATE not in", values, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("EXP_DATE between", value1, value2, "expDate");
            return (Criteria) this;
        }

        public Criteria andExpDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("EXP_DATE not between", value1, value2, "expDate");
            return (Criteria) this;
        }

        public Criteria andWxNicknameIsNull() {
            addCriterion("WX_NICKNAME is null");
            return (Criteria) this;
        }

        public Criteria andWxNicknameIsNotNull() {
            addCriterion("WX_NICKNAME is not null");
            return (Criteria) this;
        }

        public Criteria andWxNicknameEqualTo(String value) {
            addCriterion("WX_NICKNAME =", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameNotEqualTo(String value) {
            addCriterion("WX_NICKNAME <>", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameGreaterThan(String value) {
            addCriterion("WX_NICKNAME >", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("WX_NICKNAME >=", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameLessThan(String value) {
            addCriterion("WX_NICKNAME <", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameLessThanOrEqualTo(String value) {
            addCriterion("WX_NICKNAME <=", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameLike(String value) {
            addCriterion("WX_NICKNAME like", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameNotLike(String value) {
            addCriterion("WX_NICKNAME not like", value, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameIn(List<String> values) {
            addCriterion("WX_NICKNAME in", values, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameNotIn(List<String> values) {
            addCriterion("WX_NICKNAME not in", values, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameBetween(String value1, String value2) {
            addCriterion("WX_NICKNAME between", value1, value2, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andWxNicknameNotBetween(String value1, String value2) {
            addCriterion("WX_NICKNAME not between", value1, value2, "wxNickname");
            return (Criteria) this;
        }

        public Criteria andCertRemarkIsNull() {
            addCriterion("CERT_REMARK is null");
            return (Criteria) this;
        }

        public Criteria andCertRemarkIsNotNull() {
            addCriterion("CERT_REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andCertRemarkEqualTo(String value) {
            addCriterion("CERT_REMARK =", value, "certRemark");
            return (Criteria) this;
        }

        public Criteria andCertRemarkNotEqualTo(String value) {
            addCriterion("CERT_REMARK <>", value, "certRemark");
            return (Criteria) this;
        }

        public Criteria andCertRemarkGreaterThan(String value) {
            addCriterion("CERT_REMARK >", value, "certRemark");
            return (Criteria) this;
        }

        public Criteria andCertRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("CERT_REMARK >=", value, "certRemark");
            return (Criteria) this;
        }

        public Criteria andCertRemarkLessThan(String value) {
            addCriterion("CERT_REMARK <", value, "certRemark");
            return (Criteria) this;
        }

        public Criteria andCertRemarkLessThanOrEqualTo(String value) {
            addCriterion("CERT_REMARK <=", value, "certRemark");
            return (Criteria) this;
        }

        public Criteria andCertRemarkLike(String value) {
            addCriterion("CERT_REMARK like", value, "certRemark");
            return (Criteria) this;
        }

        public Criteria andCertRemarkNotLike(String value) {
            addCriterion("CERT_REMARK not like", value, "certRemark");
            return (Criteria) this;
        }

        public Criteria andCertRemarkIn(List<String> values) {
            addCriterion("CERT_REMARK in", values, "certRemark");
            return (Criteria) this;
        }

        public Criteria andCertRemarkNotIn(List<String> values) {
            addCriterion("CERT_REMARK not in", values, "certRemark");
            return (Criteria) this;
        }

        public Criteria andCertRemarkBetween(String value1, String value2) {
            addCriterion("CERT_REMARK between", value1, value2, "certRemark");
            return (Criteria) this;
        }

        public Criteria andCertRemarkNotBetween(String value1, String value2) {
            addCriterion("CERT_REMARK not between", value1, value2, "certRemark");
            return (Criteria) this;
        }

        public Criteria andHomePageBgIsNull() {
            addCriterion("HOME_PAGE_BG is null");
            return (Criteria) this;
        }

        public Criteria andHomePageBgIsNotNull() {
            addCriterion("HOME_PAGE_BG is not null");
            return (Criteria) this;
        }

        public Criteria andHomePageBgEqualTo(String value) {
            addCriterion("HOME_PAGE_BG =", value, "homePageBg");
            return (Criteria) this;
        }

        public Criteria andHomePageBgNotEqualTo(String value) {
            addCriterion("HOME_PAGE_BG <>", value, "homePageBg");
            return (Criteria) this;
        }

        public Criteria andHomePageBgGreaterThan(String value) {
            addCriterion("HOME_PAGE_BG >", value, "homePageBg");
            return (Criteria) this;
        }

        public Criteria andHomePageBgGreaterThanOrEqualTo(String value) {
            addCriterion("HOME_PAGE_BG >=", value, "homePageBg");
            return (Criteria) this;
        }

        public Criteria andHomePageBgLessThan(String value) {
            addCriterion("HOME_PAGE_BG <", value, "homePageBg");
            return (Criteria) this;
        }

        public Criteria andHomePageBgLessThanOrEqualTo(String value) {
            addCriterion("HOME_PAGE_BG <=", value, "homePageBg");
            return (Criteria) this;
        }

        public Criteria andHomePageBgLike(String value) {
            addCriterion("HOME_PAGE_BG like", value, "homePageBg");
            return (Criteria) this;
        }

        public Criteria andHomePageBgNotLike(String value) {
            addCriterion("HOME_PAGE_BG not like", value, "homePageBg");
            return (Criteria) this;
        }

        public Criteria andHomePageBgIn(List<String> values) {
            addCriterion("HOME_PAGE_BG in", values, "homePageBg");
            return (Criteria) this;
        }

        public Criteria andHomePageBgNotIn(List<String> values) {
            addCriterion("HOME_PAGE_BG not in", values, "homePageBg");
            return (Criteria) this;
        }

        public Criteria andHomePageBgBetween(String value1, String value2) {
            addCriterion("HOME_PAGE_BG between", value1, value2, "homePageBg");
            return (Criteria) this;
        }

        public Criteria andHomePageBgNotBetween(String value1, String value2) {
            addCriterion("HOME_PAGE_BG not between", value1, value2, "homePageBg");
            return (Criteria) this;
        }

        public Criteria andAuthStsIsNull() {
            addCriterion("AUTH_STS is null");
            return (Criteria) this;
        }

        public Criteria andAuthStsIsNotNull() {
            addCriterion("AUTH_STS is not null");
            return (Criteria) this;
        }

        public Criteria andAuthStsEqualTo(String value) {
            addCriterion("AUTH_STS =", value, "authSts");
            return (Criteria) this;
        }

        public Criteria andAuthStsNotEqualTo(String value) {
            addCriterion("AUTH_STS <>", value, "authSts");
            return (Criteria) this;
        }

        public Criteria andAuthStsGreaterThan(String value) {
            addCriterion("AUTH_STS >", value, "authSts");
            return (Criteria) this;
        }

        public Criteria andAuthStsGreaterThanOrEqualTo(String value) {
            addCriterion("AUTH_STS >=", value, "authSts");
            return (Criteria) this;
        }

        public Criteria andAuthStsLessThan(String value) {
            addCriterion("AUTH_STS <", value, "authSts");
            return (Criteria) this;
        }

        public Criteria andAuthStsLessThanOrEqualTo(String value) {
            addCriterion("AUTH_STS <=", value, "authSts");
            return (Criteria) this;
        }

        public Criteria andAuthStsLike(String value) {
            addCriterion("AUTH_STS like", value, "authSts");
            return (Criteria) this;
        }

        public Criteria andAuthStsNotLike(String value) {
            addCriterion("AUTH_STS not like", value, "authSts");
            return (Criteria) this;
        }

        public Criteria andAuthStsIn(List<String> values) {
            addCriterion("AUTH_STS in", values, "authSts");
            return (Criteria) this;
        }

        public Criteria andAuthStsNotIn(List<String> values) {
            addCriterion("AUTH_STS not in", values, "authSts");
            return (Criteria) this;
        }

        public Criteria andAuthStsBetween(String value1, String value2) {
            addCriterion("AUTH_STS between", value1, value2, "authSts");
            return (Criteria) this;
        }

        public Criteria andAuthStsNotBetween(String value1, String value2) {
            addCriterion("AUTH_STS not between", value1, value2, "authSts");
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