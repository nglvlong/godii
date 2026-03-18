package com.fpt.godii.application.domain.account.repository;

import com.fpt.godii.application.base.response.PaginationDTO;
import com.fpt.godii.application.domain.account.model.entity.Account;
import com.fpt.godii.application.domain.account.model.request.GetAllAccountRequest;
import com.fpt.godii.application.utils.StringUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import jakarta.persistence.criteria.*;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class AccountCriteriaRepositoryImpl implements AccountCriteriaRepository {
    private final EntityManager entityManager;
    @Override
    public PaginationDTO<Account> getAll(GetAllAccountRequest getAllAccountRequest) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Account> criteriaQuery = builder.createQuery(Account.class);
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        Root<Account> root = criteriaQuery.from(Account.class);
        Root<Account> rootCount = countQuery.from(Account.class);

        criteriaQuery.select(root);
        countQuery.select(builder.count(rootCount));

        List<Predicate> predicates = new ArrayList<>();
        List<Predicate> countPredicates = new ArrayList<>();
        if (!ObjectUtils.isEmpty(getAllAccountRequest.getSearch())) {
            predicates.add(builder.like(root.get("projectName").as(String.class), StringUtil.formatInput(getAllAccountRequest.getSearch()), '\\'));
            countPredicates.add(builder.like(rootCount.get("projectName").as(String.class), StringUtil.formatInput(getAllAccountRequest.getSearch()), '\\'));
        }

        var pageable = getAllAccountRequest.toPage();
        List<Order> orderList = new ArrayList();

        if(Objects.nonNull(pageable) && !pageable.getSort().isEmpty()){
            var order = pageable.getSort().stream().findFirst();
            if(order.get().isAscending())
                orderList.add(builder.asc(root.get(order.get().getProperty())));
            else
                orderList.add(builder.desc(root.get(order.get().getProperty())));

        }

        orderList.add(builder.desc(root.get("createdDate")));
        criteriaQuery.orderBy(orderList);

        criteriaQuery.where(builder.and(predicates.toArray(new Predicate[0])));
        countQuery.where(builder.and(countPredicates.toArray(new Predicate[0])));
        List<Account> result = Objects.nonNull(pageable) ? entityManager.createQuery(criteriaQuery)
                                                                    .setFirstResult((int) pageable.getOffset())
                                                                    .setMaxResults(pageable.getPageSize())
                                                                    .getResultList()
                                                           : entityManager.createQuery(criteriaQuery).getResultList();

        Long count = entityManager.createQuery(countQuery).getSingleResult();
        return new PaginationDTO<Account>(result, count);
    }
}
