package com.the.harbor.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HyPaymentOrderCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public HyPaymentOrderCriteria() {
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

        public Criteria andPayTypeIsNull() {
            addCriterion("PAY_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("PAY_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(String value) {
            addCriterion("PAY_TYPE =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(String value) {
            addCriterion("PAY_TYPE <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(String value) {
            addCriterion("PAY_TYPE >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_TYPE >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(String value) {
            addCriterion("PAY_TYPE <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(String value) {
            addCriterion("PAY_TYPE <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLike(String value) {
            addCriterion("PAY_TYPE like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotLike(String value) {
            addCriterion("PAY_TYPE not like", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<String> values) {
            addCriterion("PAY_TYPE in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<String> values) {
            addCriterion("PAY_TYPE not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(String value1, String value2) {
            addCriterion("PAY_TYPE between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(String value1, String value2) {
            addCriterion("PAY_TYPE not between", value1, value2, "payType");
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

        public Criteria andPayDateIsNull() {
            addCriterion("PAY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andPayDateIsNotNull() {
            addCriterion("PAY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andPayDateEqualTo(Timestamp value) {
            addCriterion("PAY_DATE =", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotEqualTo(Timestamp value) {
            addCriterion("PAY_DATE <>", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateGreaterThan(Timestamp value) {
            addCriterion("PAY_DATE >", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("PAY_DATE >=", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateLessThan(Timestamp value) {
            addCriterion("PAY_DATE <", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("PAY_DATE <=", value, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateIn(List<Timestamp> values) {
            addCriterion("PAY_DATE in", values, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotIn(List<Timestamp> values) {
            addCriterion("PAY_DATE not in", values, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PAY_DATE between", value1, value2, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("PAY_DATE not between", value1, value2, "payDate");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNull() {
            addCriterion("PAY_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNotNull() {
            addCriterion("PAY_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andPayStatusEqualTo(String value) {
            addCriterion("PAY_STATUS =", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotEqualTo(String value) {
            addCriterion("PAY_STATUS <>", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThan(String value) {
            addCriterion("PAY_STATUS >", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThanOrEqualTo(String value) {
            addCriterion("PAY_STATUS >=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThan(String value) {
            addCriterion("PAY_STATUS <", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThanOrEqualTo(String value) {
            addCriterion("PAY_STATUS <=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLike(String value) {
            addCriterion("PAY_STATUS like", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotLike(String value) {
            addCriterion("PAY_STATUS not like", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusIn(List<String> values) {
            addCriterion("PAY_STATUS in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotIn(List<String> values) {
            addCriterion("PAY_STATUS not in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusBetween(String value1, String value2) {
            addCriterion("PAY_STATUS between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotBetween(String value1, String value2) {
            addCriterion("PAY_STATUS not between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andNotifyDateIsNull() {
            addCriterion("NOTIFY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andNotifyDateIsNotNull() {
            addCriterion("NOTIFY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyDateEqualTo(Timestamp value) {
            addCriterion("NOTIFY_DATE =", value, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateNotEqualTo(Timestamp value) {
            addCriterion("NOTIFY_DATE <>", value, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateGreaterThan(Timestamp value) {
            addCriterion("NOTIFY_DATE >", value, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("NOTIFY_DATE >=", value, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateLessThan(Timestamp value) {
            addCriterion("NOTIFY_DATE <", value, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("NOTIFY_DATE <=", value, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateIn(List<Timestamp> values) {
            addCriterion("NOTIFY_DATE in", values, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateNotIn(List<Timestamp> values) {
            addCriterion("NOTIFY_DATE not in", values, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("NOTIFY_DATE between", value1, value2, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andNotifyDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("NOTIFY_DATE not between", value1, value2, "notifyDate");
            return (Criteria) this;
        }

        public Criteria andTransactionIdIsNull() {
            addCriterion("transaction_id is null");
            return (Criteria) this;
        }

        public Criteria andTransactionIdIsNotNull() {
            addCriterion("transaction_id is not null");
            return (Criteria) this;
        }

        public Criteria andTransactionIdEqualTo(String value) {
            addCriterion("transaction_id =", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdNotEqualTo(String value) {
            addCriterion("transaction_id <>", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdGreaterThan(String value) {
            addCriterion("transaction_id >", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdGreaterThanOrEqualTo(String value) {
            addCriterion("transaction_id >=", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdLessThan(String value) {
            addCriterion("transaction_id <", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdLessThanOrEqualTo(String value) {
            addCriterion("transaction_id <=", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdLike(String value) {
            addCriterion("transaction_id like", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdNotLike(String value) {
            addCriterion("transaction_id not like", value, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdIn(List<String> values) {
            addCriterion("transaction_id in", values, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdNotIn(List<String> values) {
            addCriterion("transaction_id not in", values, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdBetween(String value1, String value2) {
            addCriterion("transaction_id between", value1, value2, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTransactionIdNotBetween(String value1, String value2) {
            addCriterion("transaction_id not between", value1, value2, "transactionId");
            return (Criteria) this;
        }

        public Criteria andTimeEndIsNull() {
            addCriterion("time_end is null");
            return (Criteria) this;
        }

        public Criteria andTimeEndIsNotNull() {
            addCriterion("time_end is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEndEqualTo(String value) {
            addCriterion("time_end =", value, "timeEnd");
            return (Criteria) this;
        }

        public Criteria andTimeEndNotEqualTo(String value) {
            addCriterion("time_end <>", value, "timeEnd");
            return (Criteria) this;
        }

        public Criteria andTimeEndGreaterThan(String value) {
            addCriterion("time_end >", value, "timeEnd");
            return (Criteria) this;
        }

        public Criteria andTimeEndGreaterThanOrEqualTo(String value) {
            addCriterion("time_end >=", value, "timeEnd");
            return (Criteria) this;
        }

        public Criteria andTimeEndLessThan(String value) {
            addCriterion("time_end <", value, "timeEnd");
            return (Criteria) this;
        }

        public Criteria andTimeEndLessThanOrEqualTo(String value) {
            addCriterion("time_end <=", value, "timeEnd");
            return (Criteria) this;
        }

        public Criteria andTimeEndLike(String value) {
            addCriterion("time_end like", value, "timeEnd");
            return (Criteria) this;
        }

        public Criteria andTimeEndNotLike(String value) {
            addCriterion("time_end not like", value, "timeEnd");
            return (Criteria) this;
        }

        public Criteria andTimeEndIn(List<String> values) {
            addCriterion("time_end in", values, "timeEnd");
            return (Criteria) this;
        }

        public Criteria andTimeEndNotIn(List<String> values) {
            addCriterion("time_end not in", values, "timeEnd");
            return (Criteria) this;
        }

        public Criteria andTimeEndBetween(String value1, String value2) {
            addCriterion("time_end between", value1, value2, "timeEnd");
            return (Criteria) this;
        }

        public Criteria andTimeEndNotBetween(String value1, String value2) {
            addCriterion("time_end not between", value1, value2, "timeEnd");
            return (Criteria) this;
        }

        public Criteria andResultCodeIsNull() {
            addCriterion("result_code is null");
            return (Criteria) this;
        }

        public Criteria andResultCodeIsNotNull() {
            addCriterion("result_code is not null");
            return (Criteria) this;
        }

        public Criteria andResultCodeEqualTo(String value) {
            addCriterion("result_code =", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeNotEqualTo(String value) {
            addCriterion("result_code <>", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeGreaterThan(String value) {
            addCriterion("result_code >", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeGreaterThanOrEqualTo(String value) {
            addCriterion("result_code >=", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeLessThan(String value) {
            addCriterion("result_code <", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeLessThanOrEqualTo(String value) {
            addCriterion("result_code <=", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeLike(String value) {
            addCriterion("result_code like", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeNotLike(String value) {
            addCriterion("result_code not like", value, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeIn(List<String> values) {
            addCriterion("result_code in", values, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeNotIn(List<String> values) {
            addCriterion("result_code not in", values, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeBetween(String value1, String value2) {
            addCriterion("result_code between", value1, value2, "resultCode");
            return (Criteria) this;
        }

        public Criteria andResultCodeNotBetween(String value1, String value2) {
            addCriterion("result_code not between", value1, value2, "resultCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeIsNull() {
            addCriterion("return_code is null");
            return (Criteria) this;
        }

        public Criteria andReturnCodeIsNotNull() {
            addCriterion("return_code is not null");
            return (Criteria) this;
        }

        public Criteria andReturnCodeEqualTo(String value) {
            addCriterion("return_code =", value, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeNotEqualTo(String value) {
            addCriterion("return_code <>", value, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeGreaterThan(String value) {
            addCriterion("return_code >", value, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeGreaterThanOrEqualTo(String value) {
            addCriterion("return_code >=", value, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeLessThan(String value) {
            addCriterion("return_code <", value, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeLessThanOrEqualTo(String value) {
            addCriterion("return_code <=", value, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeLike(String value) {
            addCriterion("return_code like", value, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeNotLike(String value) {
            addCriterion("return_code not like", value, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeIn(List<String> values) {
            addCriterion("return_code in", values, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeNotIn(List<String> values) {
            addCriterion("return_code not in", values, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeBetween(String value1, String value2) {
            addCriterion("return_code between", value1, value2, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnCodeNotBetween(String value1, String value2) {
            addCriterion("return_code not between", value1, value2, "returnCode");
            return (Criteria) this;
        }

        public Criteria andReturnMsgIsNull() {
            addCriterion("return_msg is null");
            return (Criteria) this;
        }

        public Criteria andReturnMsgIsNotNull() {
            addCriterion("return_msg is not null");
            return (Criteria) this;
        }

        public Criteria andReturnMsgEqualTo(String value) {
            addCriterion("return_msg =", value, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgNotEqualTo(String value) {
            addCriterion("return_msg <>", value, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgGreaterThan(String value) {
            addCriterion("return_msg >", value, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgGreaterThanOrEqualTo(String value) {
            addCriterion("return_msg >=", value, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgLessThan(String value) {
            addCriterion("return_msg <", value, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgLessThanOrEqualTo(String value) {
            addCriterion("return_msg <=", value, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgLike(String value) {
            addCriterion("return_msg like", value, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgNotLike(String value) {
            addCriterion("return_msg not like", value, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgIn(List<String> values) {
            addCriterion("return_msg in", values, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgNotIn(List<String> values) {
            addCriterion("return_msg not in", values, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgBetween(String value1, String value2) {
            addCriterion("return_msg between", value1, value2, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andReturnMsgNotBetween(String value1, String value2) {
            addCriterion("return_msg not between", value1, value2, "returnMsg");
            return (Criteria) this;
        }

        public Criteria andNotifyParamIsNull() {
            addCriterion("notify_param is null");
            return (Criteria) this;
        }

        public Criteria andNotifyParamIsNotNull() {
            addCriterion("notify_param is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyParamEqualTo(String value) {
            addCriterion("notify_param =", value, "notifyParam");
            return (Criteria) this;
        }

        public Criteria andNotifyParamNotEqualTo(String value) {
            addCriterion("notify_param <>", value, "notifyParam");
            return (Criteria) this;
        }

        public Criteria andNotifyParamGreaterThan(String value) {
            addCriterion("notify_param >", value, "notifyParam");
            return (Criteria) this;
        }

        public Criteria andNotifyParamGreaterThanOrEqualTo(String value) {
            addCriterion("notify_param >=", value, "notifyParam");
            return (Criteria) this;
        }

        public Criteria andNotifyParamLessThan(String value) {
            addCriterion("notify_param <", value, "notifyParam");
            return (Criteria) this;
        }

        public Criteria andNotifyParamLessThanOrEqualTo(String value) {
            addCriterion("notify_param <=", value, "notifyParam");
            return (Criteria) this;
        }

        public Criteria andNotifyParamLike(String value) {
            addCriterion("notify_param like", value, "notifyParam");
            return (Criteria) this;
        }

        public Criteria andNotifyParamNotLike(String value) {
            addCriterion("notify_param not like", value, "notifyParam");
            return (Criteria) this;
        }

        public Criteria andNotifyParamIn(List<String> values) {
            addCriterion("notify_param in", values, "notifyParam");
            return (Criteria) this;
        }

        public Criteria andNotifyParamNotIn(List<String> values) {
            addCriterion("notify_param not in", values, "notifyParam");
            return (Criteria) this;
        }

        public Criteria andNotifyParamBetween(String value1, String value2) {
            addCriterion("notify_param between", value1, value2, "notifyParam");
            return (Criteria) this;
        }

        public Criteria andNotifyParamNotBetween(String value1, String value2) {
            addCriterion("notify_param not between", value1, value2, "notifyParam");
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