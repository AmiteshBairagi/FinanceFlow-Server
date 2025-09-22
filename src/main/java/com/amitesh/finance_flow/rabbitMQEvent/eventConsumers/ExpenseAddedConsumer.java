package com.amitesh.finance_flow.rabbitMQEvent.eventConsumers;

import com.amitesh.finance_flow.model.budgets.Budget;
import com.amitesh.finance_flow.model.budgets.UserBudgets;
import com.amitesh.finance_flow.rabbitMQEvent.transactionEvent.ExpenseAddedEvent;
import com.amitesh.finance_flow.repo.budgets.UserBudgetsRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static com.amitesh.finance_flow.config.RabbitMQConfig.EXPENSE_QUEUE;

@Service
public class ExpenseAddedConsumer {
    private final UserBudgetsRepository repo;

    public ExpenseAddedConsumer(UserBudgetsRepository repo) {
        this.repo = repo;
    }

    @RabbitListener(queues = EXPENSE_QUEUE)
    public void handleExpenseAddedEvent(ExpenseAddedEvent event){
        System.out.println(event);
        String userId = event.getUserId();
        String categoryId = event.getCategoryId();
        double amountSpent = event.getAmountSpent();


        UserBudgets userBudgets = repo.findByUserId(event.getUserId());
        if(userBudgets != null){
            List<Budget> budgets = userBudgets.getBudgets();

            Budget matchedBudget = budgets.stream()
                    .filter(budget -> budget.getCategoryId().equals(categoryId))
                    .findFirst()
                    .orElse(null);

            if(matchedBudget != null){
                matchedBudget.setUsedAmount(matchedBudget.getUsedAmount() + amountSpent);
                matchedBudget.setUpdatedAt(Instant.now());
                repo.save(userBudgets);
                System.out.println("The Budget with categoryId : " + categoryId + "updated based on users added expense");
            }
        }

    }
}
