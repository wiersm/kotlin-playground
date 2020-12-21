package playground.advent2020.day21

import playground.utilities.Logger

class FoodAnalyser(val l: Logger) {
    fun countIngredientsWithoutAllergens(input: Sequence<String>): Int {
        val foods = parseFoods(input)
        l.debug("Read foods:\n${foods.joinToString("\n")}")

        // Map allergens to foods
        val allergenToFoodMap = mutableMapOf<String, MutableList<Food>>()
        foods.forEach { food ->
            food.allergens.forEach { allergen ->
                allergenToFoodMap.computeIfAbsent(allergen) { mutableListOf() }.add(food)
            }
        }

        // Map allergens to ingredients, taking the intersection of the foods that it is contained in
        val allergenToIngredients = mutableMapOf<String, MutableList<String>>()
        allergenToFoodMap.forEach { (allergen, foodList) ->
            allergenToIngredients[allergen] = foodList.map { food -> food.ingredients }.reduce { ingredients, nextIngredients ->
                ingredients.intersect(nextIngredients).toList()
            }.toMutableList()
        }

        l.debug("Result so far: $allergenToIngredients")

        // The ingredients that are in this map are the ones that can contain allergens
        val ingredientsWithPossibleAllergens = allergenToIngredients.values.reduce { ingredients, nextIngredients ->
            ingredients.union(nextIngredients).toMutableList()
        }

        l.debug("Ingredients that can contain allergens: $ingredientsWithPossibleAllergens")

        // We are interested in the other ingredients
        val allIngredients = foods.map { food -> food.ingredients }.reduce { ingredients, nextIngredients ->
            ingredients.union(nextIngredients).toList()
        }
        val ingredientsWithoutAllergens = allIngredients.filterNot { ingredientsWithPossibleAllergens.contains(it) }

        l.debug("Ingredients without allergens: $ingredientsWithoutAllergens")

        // Count how many times these occur
        return foods.map { food ->
            food.ingredients.filter { ingredientsWithoutAllergens.contains(it) }.count()
        }.sum()
    }

    private fun parseFoods(input: Sequence<String>): List<Food> {
        return input.map { line ->
            val split = line.split(" (contains ")
            val ingredients = split[0].split(' ')
            val allergens = if (split.size == 2) {
                split[1].dropLast(1).split(", ")
            } else {
                listOf()
            }
            Food(ingredients, allergens)
        }.toList()
    }
}

private class Food(val ingredients: List<String>, val allergens: List<String>) {
    override fun toString(): String {
        return "$ingredients - $allergens"
    }
}
