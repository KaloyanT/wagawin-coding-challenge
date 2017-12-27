package com.wagawin.wagawincodingtask.repository;

import com.wagawin.wagawincodingtask.model.Meal;
import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<Meal, Long> {

    Meal findByMealId(final long mealId);
}
