package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HyGoOrderCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyGoOrderCriteria() {
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

        public Criteria andQuestionsIsNull() {
            addCriterion("QUESTIONS is null");
            return (Criteria) this;
        }

        public Criteria andQuestionsIsNotNull() {
            addCriterion("QUESTIONS is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionsEqualTo(String value) {
            addCriterion("QUESTIONS =", value, "questions");
            return (Criteria) this;
        }

        public Criteria andQuestionsNotEqualTo(String value) {
            addCriterion("QUESTIONS <>", value, "questions");
            return (Criteria) this;
        }

        public Criteria andQuestionsGreaterThan(String value) {
            addCriterion("QUESTIONS >", value, "questions");
            return (Criteria) this;
        }

        public Criteria andQuestionsGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTIONS >=", value, "questions");
            return (Criteria) this;
        }

        public Criteria andQuestionsLessThan(String value) {
            addCriterion("QUESTIONS <", value, "questions");
            return (Criteria) this;
        }

        public Criteria andQuestionsLessThanOrEqualTo(String value) {
            addCriterion("QUESTIONS <=", value, "questions");
            return (Criteria) this;
        }

        public Criteria andQuestionsLike(String value) {
            addCriterion("QUESTIONS like", value, "questions");
            return (Criteria) this;
        }

        public Criteria andQuestionsNotLike(String value) {
            addCriterion("QUESTIONS not like", value, "questions");
            return (Criteria) this;
        }

        public Criteria andQuestionsIn(List<String> values) {
            addCriterion("QUESTIONS in", values, "questions");
            return (Criteria) this;
        }

        public Criteria andQuestionsNotIn(List<String> values) {
            addCriterion("QUESTIONS not in", values, "questions");
            return (Criteria) this;
        }

        public Criteria andQuestionsBetween(String value1, String value2) {
            addCriterion("QUESTIONS between", value1, value2, "questions");
            return (Criteria) this;
        }

        public Criteria andQuestionsNotBetween(String value1, String value2) {
            addCriterion("QUESTIONS not between", value1, value2, "questions");
            return (Criteria) this;
        }

        public Criteria andSelfIntroIsNull() {
            addCriterion("SELF_INTRO is null");
            return (Criteria) this;
        }

        public Criteria andSelfIntroIsNotNull() {
            addCriterion("SELF_INTRO is not null");
            return (Criteria) this;
        }

        public Criteria andSelfIntroEqualTo(String value) {
            addCriterion("SELF_INTRO =", value, "selfIntro");
            return (Criteria) this;
        }

        public Criteria andSelfIntroNotEqualTo(String value) {
            addCriterion("SELF_INTRO <>", value, "selfIntro");
            return (Criteria) this;
        }

        public Criteria andSelfIntroGreaterThan(String value) {
            addCriterion("SELF_INTRO >", value, "selfIntro");
            return (Criteria) this;
        }

        public Criteria andSelfIntroGreaterThanOrEqualTo(String value) {
            addCriterion("SELF_INTRO >=", value, "selfIntro");
            return (Criteria) this;
        }

        public Criteria andSelfIntroLessThan(String value) {
            addCriterion("SELF_INTRO <", value, "selfIntro");
            return (Criteria) this;
        }

        public Criteria andSelfIntroLessThanOrEqualTo(String value) {
            addCriterion("SELF_INTRO <=", value, "selfIntro");
            return (Criteria) this;
        }

        public Criteria andSelfIntroLike(String value) {
            addCriterion("SELF_INTRO like", value, "selfIntro");
            return (Criteria) this;
        }

        public Criteria andSelfIntroNotLike(String value) {
            addCriterion("SELF_INTRO not like", value, "selfIntro");
            return (Criteria) this;
        }

        public Criteria andSelfIntroIn(List<String> values) {
            addCriterion("SELF_INTRO in", values, "selfIntro");
            return (Criteria) this;
        }

        public Criteria andSelfIntroNotIn(List<String> values) {
            addCriterion("SELF_INTRO not in", values, "selfIntro");
            return (Criteria) this;
        }

        public Criteria andSelfIntroBetween(String value1, String value2) {
            addCriterion("SELF_INTRO between", value1, value2, "selfIntro");
            return (Criteria) this;
        }

        public Criteria andSelfIntroNotBetween(String value1, String value2) {
            addCriterion("SELF_INTRO not between", value1, value2, "selfIntro");
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

        public Criteria andConfirmDateIsNull() {
            addCriterion("CONFIRM_DATE is null");
            return (Criteria) this;
        }

        public Criteria andConfirmDateIsNotNull() {
            addCriterion("CONFIRM_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmDateEqualTo(Timestamp value) {
            addCriterion("CONFIRM_DATE =", value, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateNotEqualTo(Timestamp value) {
            addCriterion("CONFIRM_DATE <>", value, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateGreaterThan(Timestamp value) {
            addCriterion("CONFIRM_DATE >", value, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CONFIRM_DATE >=", value, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateLessThan(Timestamp value) {
            addCriterion("CONFIRM_DATE <", value, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("CONFIRM_DATE <=", value, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateIn(List<Timestamp> values) {
            addCriterion("CONFIRM_DATE in", values, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateNotIn(List<Timestamp> values) {
            addCriterion("CONFIRM_DATE not in", values, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CONFIRM_DATE between", value1, value2, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andConfirmDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CONFIRM_DATE not between", value1, value2, "confirmDate");
            return (Criteria) this;
        }

        public Criteria andExpectedTime1IsNull() {
            addCriterion("EXPECTED_TIME1 is null");
            return (Criteria) this;
        }

        public Criteria andExpectedTime1IsNotNull() {
            addCriterion("EXPECTED_TIME1 is not null");
            return (Criteria) this;
        }

        public Criteria andExpectedTime1EqualTo(String value) {
            addCriterion("EXPECTED_TIME1 =", value, "expectedTime1");
            return (Criteria) this;
        }

        public Criteria andExpectedTime1NotEqualTo(String value) {
            addCriterion("EXPECTED_TIME1 <>", value, "expectedTime1");
            return (Criteria) this;
        }

        public Criteria andExpectedTime1GreaterThan(String value) {
            addCriterion("EXPECTED_TIME1 >", value, "expectedTime1");
            return (Criteria) this;
        }

        public Criteria andExpectedTime1GreaterThanOrEqualTo(String value) {
            addCriterion("EXPECTED_TIME1 >=", value, "expectedTime1");
            return (Criteria) this;
        }

        public Criteria andExpectedTime1LessThan(String value) {
            addCriterion("EXPECTED_TIME1 <", value, "expectedTime1");
            return (Criteria) this;
        }

        public Criteria andExpectedTime1LessThanOrEqualTo(String value) {
            addCriterion("EXPECTED_TIME1 <=", value, "expectedTime1");
            return (Criteria) this;
        }

        public Criteria andExpectedTime1Like(String value) {
            addCriterion("EXPECTED_TIME1 like", value, "expectedTime1");
            return (Criteria) this;
        }

        public Criteria andExpectedTime1NotLike(String value) {
            addCriterion("EXPECTED_TIME1 not like", value, "expectedTime1");
            return (Criteria) this;
        }

        public Criteria andExpectedTime1In(List<String> values) {
            addCriterion("EXPECTED_TIME1 in", values, "expectedTime1");
            return (Criteria) this;
        }

        public Criteria andExpectedTime1NotIn(List<String> values) {
            addCriterion("EXPECTED_TIME1 not in", values, "expectedTime1");
            return (Criteria) this;
        }

        public Criteria andExpectedTime1Between(String value1, String value2) {
            addCriterion("EXPECTED_TIME1 between", value1, value2, "expectedTime1");
            return (Criteria) this;
        }

        public Criteria andExpectedTime1NotBetween(String value1, String value2) {
            addCriterion("EXPECTED_TIME1 not between", value1, value2, "expectedTime1");
            return (Criteria) this;
        }

        public Criteria andExpectedTime2IsNull() {
            addCriterion("EXPECTED_TIME2 is null");
            return (Criteria) this;
        }

        public Criteria andExpectedTime2IsNotNull() {
            addCriterion("EXPECTED_TIME2 is not null");
            return (Criteria) this;
        }

        public Criteria andExpectedTime2EqualTo(String value) {
            addCriterion("EXPECTED_TIME2 =", value, "expectedTime2");
            return (Criteria) this;
        }

        public Criteria andExpectedTime2NotEqualTo(String value) {
            addCriterion("EXPECTED_TIME2 <>", value, "expectedTime2");
            return (Criteria) this;
        }

        public Criteria andExpectedTime2GreaterThan(String value) {
            addCriterion("EXPECTED_TIME2 >", value, "expectedTime2");
            return (Criteria) this;
        }

        public Criteria andExpectedTime2GreaterThanOrEqualTo(String value) {
            addCriterion("EXPECTED_TIME2 >=", value, "expectedTime2");
            return (Criteria) this;
        }

        public Criteria andExpectedTime2LessThan(String value) {
            addCriterion("EXPECTED_TIME2 <", value, "expectedTime2");
            return (Criteria) this;
        }

        public Criteria andExpectedTime2LessThanOrEqualTo(String value) {
            addCriterion("EXPECTED_TIME2 <=", value, "expectedTime2");
            return (Criteria) this;
        }

        public Criteria andExpectedTime2Like(String value) {
            addCriterion("EXPECTED_TIME2 like", value, "expectedTime2");
            return (Criteria) this;
        }

        public Criteria andExpectedTime2NotLike(String value) {
            addCriterion("EXPECTED_TIME2 not like", value, "expectedTime2");
            return (Criteria) this;
        }

        public Criteria andExpectedTime2In(List<String> values) {
            addCriterion("EXPECTED_TIME2 in", values, "expectedTime2");
            return (Criteria) this;
        }

        public Criteria andExpectedTime2NotIn(List<String> values) {
            addCriterion("EXPECTED_TIME2 not in", values, "expectedTime2");
            return (Criteria) this;
        }

        public Criteria andExpectedTime2Between(String value1, String value2) {
            addCriterion("EXPECTED_TIME2 between", value1, value2, "expectedTime2");
            return (Criteria) this;
        }

        public Criteria andExpectedTime2NotBetween(String value1, String value2) {
            addCriterion("EXPECTED_TIME2 not between", value1, value2, "expectedTime2");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation1IsNull() {
            addCriterion("EXPECTED_LOCATION1 is null");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation1IsNotNull() {
            addCriterion("EXPECTED_LOCATION1 is not null");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation1EqualTo(String value) {
            addCriterion("EXPECTED_LOCATION1 =", value, "expectedLocation1");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation1NotEqualTo(String value) {
            addCriterion("EXPECTED_LOCATION1 <>", value, "expectedLocation1");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation1GreaterThan(String value) {
            addCriterion("EXPECTED_LOCATION1 >", value, "expectedLocation1");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation1GreaterThanOrEqualTo(String value) {
            addCriterion("EXPECTED_LOCATION1 >=", value, "expectedLocation1");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation1LessThan(String value) {
            addCriterion("EXPECTED_LOCATION1 <", value, "expectedLocation1");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation1LessThanOrEqualTo(String value) {
            addCriterion("EXPECTED_LOCATION1 <=", value, "expectedLocation1");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation1Like(String value) {
            addCriterion("EXPECTED_LOCATION1 like", value, "expectedLocation1");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation1NotLike(String value) {
            addCriterion("EXPECTED_LOCATION1 not like", value, "expectedLocation1");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation1In(List<String> values) {
            addCriterion("EXPECTED_LOCATION1 in", values, "expectedLocation1");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation1NotIn(List<String> values) {
            addCriterion("EXPECTED_LOCATION1 not in", values, "expectedLocation1");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation1Between(String value1, String value2) {
            addCriterion("EXPECTED_LOCATION1 between", value1, value2, "expectedLocation1");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation1NotBetween(String value1, String value2) {
            addCriterion("EXPECTED_LOCATION1 not between", value1, value2, "expectedLocation1");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation2IsNull() {
            addCriterion("EXPECTED_LOCATION2 is null");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation2IsNotNull() {
            addCriterion("EXPECTED_LOCATION2 is not null");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation2EqualTo(String value) {
            addCriterion("EXPECTED_LOCATION2 =", value, "expectedLocation2");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation2NotEqualTo(String value) {
            addCriterion("EXPECTED_LOCATION2 <>", value, "expectedLocation2");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation2GreaterThan(String value) {
            addCriterion("EXPECTED_LOCATION2 >", value, "expectedLocation2");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation2GreaterThanOrEqualTo(String value) {
            addCriterion("EXPECTED_LOCATION2 >=", value, "expectedLocation2");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation2LessThan(String value) {
            addCriterion("EXPECTED_LOCATION2 <", value, "expectedLocation2");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation2LessThanOrEqualTo(String value) {
            addCriterion("EXPECTED_LOCATION2 <=", value, "expectedLocation2");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation2Like(String value) {
            addCriterion("EXPECTED_LOCATION2 like", value, "expectedLocation2");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation2NotLike(String value) {
            addCriterion("EXPECTED_LOCATION2 not like", value, "expectedLocation2");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation2In(List<String> values) {
            addCriterion("EXPECTED_LOCATION2 in", values, "expectedLocation2");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation2NotIn(List<String> values) {
            addCriterion("EXPECTED_LOCATION2 not in", values, "expectedLocation2");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation2Between(String value1, String value2) {
            addCriterion("EXPECTED_LOCATION2 between", value1, value2, "expectedLocation2");
            return (Criteria) this;
        }

        public Criteria andExpectedLocation2NotBetween(String value1, String value2) {
            addCriterion("EXPECTED_LOCATION2 not between", value1, value2, "expectedLocation2");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIsNull() {
            addCriterion("CONFIRM_TIME is null");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIsNotNull() {
            addCriterion("CONFIRM_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeEqualTo(String value) {
            addCriterion("CONFIRM_TIME =", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotEqualTo(String value) {
            addCriterion("CONFIRM_TIME <>", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeGreaterThan(String value) {
            addCriterion("CONFIRM_TIME >", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CONFIRM_TIME >=", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeLessThan(String value) {
            addCriterion("CONFIRM_TIME <", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeLessThanOrEqualTo(String value) {
            addCriterion("CONFIRM_TIME <=", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeLike(String value) {
            addCriterion("CONFIRM_TIME like", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotLike(String value) {
            addCriterion("CONFIRM_TIME not like", value, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeIn(List<String> values) {
            addCriterion("CONFIRM_TIME in", values, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotIn(List<String> values) {
            addCriterion("CONFIRM_TIME not in", values, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeBetween(String value1, String value2) {
            addCriterion("CONFIRM_TIME between", value1, value2, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmTimeNotBetween(String value1, String value2) {
            addCriterion("CONFIRM_TIME not between", value1, value2, "confirmTime");
            return (Criteria) this;
        }

        public Criteria andConfirmLocationIsNull() {
            addCriterion("CONFIRM_LOCATION is null");
            return (Criteria) this;
        }

        public Criteria andConfirmLocationIsNotNull() {
            addCriterion("CONFIRM_LOCATION is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmLocationEqualTo(String value) {
            addCriterion("CONFIRM_LOCATION =", value, "confirmLocation");
            return (Criteria) this;
        }

        public Criteria andConfirmLocationNotEqualTo(String value) {
            addCriterion("CONFIRM_LOCATION <>", value, "confirmLocation");
            return (Criteria) this;
        }

        public Criteria andConfirmLocationGreaterThan(String value) {
            addCriterion("CONFIRM_LOCATION >", value, "confirmLocation");
            return (Criteria) this;
        }

        public Criteria andConfirmLocationGreaterThanOrEqualTo(String value) {
            addCriterion("CONFIRM_LOCATION >=", value, "confirmLocation");
            return (Criteria) this;
        }

        public Criteria andConfirmLocationLessThan(String value) {
            addCriterion("CONFIRM_LOCATION <", value, "confirmLocation");
            return (Criteria) this;
        }

        public Criteria andConfirmLocationLessThanOrEqualTo(String value) {
            addCriterion("CONFIRM_LOCATION <=", value, "confirmLocation");
            return (Criteria) this;
        }

        public Criteria andConfirmLocationLike(String value) {
            addCriterion("CONFIRM_LOCATION like", value, "confirmLocation");
            return (Criteria) this;
        }

        public Criteria andConfirmLocationNotLike(String value) {
            addCriterion("CONFIRM_LOCATION not like", value, "confirmLocation");
            return (Criteria) this;
        }

        public Criteria andConfirmLocationIn(List<String> values) {
            addCriterion("CONFIRM_LOCATION in", values, "confirmLocation");
            return (Criteria) this;
        }

        public Criteria andConfirmLocationNotIn(List<String> values) {
            addCriterion("CONFIRM_LOCATION not in", values, "confirmLocation");
            return (Criteria) this;
        }

        public Criteria andConfirmLocationBetween(String value1, String value2) {
            addCriterion("CONFIRM_LOCATION between", value1, value2, "confirmLocation");
            return (Criteria) this;
        }

        public Criteria andConfirmLocationNotBetween(String value1, String value2) {
            addCriterion("CONFIRM_LOCATION not between", value1, value2, "confirmLocation");
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

        public Criteria andPayStsDateIsNull() {
            addCriterion("PAY_STS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPayStsDateIsNotNull() {
            addCriterion("PAY_STS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPayStsDateEqualTo(Timestamp value) {
            addCriterion("PAY_STS_DATE =", value, "payStsDate");
            return (Criteria) this;
        }

        public Criteria andPayStsDateNotEqualTo(Timestamp value) {
            addCriterion("PAY_STS_DATE <>", value, "payStsDate");
            return (Criteria) this;
        }

        public Criteria andPayStsDateGreaterThan(Timestamp value) {
            addCriterion("PAY_STS_DATE >", value, "payStsDate");
            return (Criteria) this;
        }

        public Criteria andPayStsDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("PAY_STS_DATE >=", value, "payStsDate");
            return (Criteria) this;
        }

        public Criteria andPayStsDateLessThan(Timestamp value) {
            addCriterion("PAY_STS_DATE <", value, "payStsDate");
            return (Criteria) this;
        }

        public Criteria andPayStsDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("PAY_STS_DATE <=", value, "payStsDate");
            return (Criteria) this;
        }

        public Criteria andPayStsDateIn(List<Timestamp> values) {
            addCriterion("PAY_STS_DATE in", values, "payStsDate");
            return (Criteria) this;
        }

        public Criteria andPayStsDateNotIn(List<Timestamp> values) {
            addCriterion("PAY_STS_DATE not in", values, "payStsDate");
            return (Criteria) this;
        }

        public Criteria andPayStsDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PAY_STS_DATE between", value1, value2, "payStsDate");
            return (Criteria) this;
        }

        public Criteria andPayStsDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PAY_STS_DATE not between", value1, value2, "payStsDate");
            return (Criteria) this;
        }

        public Criteria andConfirmStsDateIsNull() {
            addCriterion("CONFIRM_STS_DATE is null");
            return (Criteria) this;
        }

        public Criteria andConfirmStsDateIsNotNull() {
            addCriterion("CONFIRM_STS_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andConfirmStsDateEqualTo(Timestamp value) {
            addCriterion("CONFIRM_STS_DATE =", value, "confirmStsDate");
            return (Criteria) this;
        }

        public Criteria andConfirmStsDateNotEqualTo(Timestamp value) {
            addCriterion("CONFIRM_STS_DATE <>", value, "confirmStsDate");
            return (Criteria) this;
        }

        public Criteria andConfirmStsDateGreaterThan(Timestamp value) {
            addCriterion("CONFIRM_STS_DATE >", value, "confirmStsDate");
            return (Criteria) this;
        }

        public Criteria andConfirmStsDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CONFIRM_STS_DATE >=", value, "confirmStsDate");
            return (Criteria) this;
        }

        public Criteria andConfirmStsDateLessThan(Timestamp value) {
            addCriterion("CONFIRM_STS_DATE <", value, "confirmStsDate");
            return (Criteria) this;
        }

        public Criteria andConfirmStsDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("CONFIRM_STS_DATE <=", value, "confirmStsDate");
            return (Criteria) this;
        }

        public Criteria andConfirmStsDateIn(List<Timestamp> values) {
            addCriterion("CONFIRM_STS_DATE in", values, "confirmStsDate");
            return (Criteria) this;
        }

        public Criteria andConfirmStsDateNotIn(List<Timestamp> values) {
            addCriterion("CONFIRM_STS_DATE not in", values, "confirmStsDate");
            return (Criteria) this;
        }

        public Criteria andConfirmStsDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CONFIRM_STS_DATE between", value1, value2, "confirmStsDate");
            return (Criteria) this;
        }

        public Criteria andConfirmStsDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CONFIRM_STS_DATE not between", value1, value2, "confirmStsDate");
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