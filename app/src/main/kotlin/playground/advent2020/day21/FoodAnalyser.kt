package playground.advent2020.day21

import playground.utilities.Logger

class FoodAnalyser(val l: Logger) {
    fun countIngredientsWithoutAllergens(input: Sequence<String>): Int {
        val foods = parseFoods(input)
        l.debug("Read foods:\n${foods.joinToString("\n")}")

        // Create a map of allergens to possible ingredients that might contain them
        val allergenToIngredients = constructAllergenToIngredientMap(foods)

        // Take the ingredients that are in this map
        val ingredientsWithPossibleAllergens = allergenToIngredients.values.reduce { ingredients, nextIngredients ->
            ingredients.union(nextIngredients).toMutableList()
        }

        l.debug("Ingredients that can contain allergens: $ingredientsWithPossibleAllergens")

        // Now create the list of other ingredient
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

    private fun constructAllergenToIngredientMap(foods: List<Food>): MutableMap<String, MutableList<String>> {
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
        return allergenToIngredients
    }

    fun listIngredientsWithAllergens(input: Sequence<String>): String {
        val foods = parseFoods(input)

        // Again create the map of allergens to possible ingredients that contain them
        val allergenToIngredients = constructAllergenToIngredientMap(foods)

        // We're going to create a map of confirmed allergens step by step
        val confirmedAllergenIngredients = sortedMapOf<String, String>()

        // Eliminate ingredients until we have confirmed them all
        // It should be necessary to do this at most once for each allergen
        repeat(allergenToIngredients.size) {
            // Find allergens that map to only one ingredient
            allergenToIngredients.filterValues {
                ingredients -> ingredients.size == 1
            }.forEach { (allergen, ingredients) ->
                // Add these to the map of confirmed ingredients
                val confirmedIngredient = ingredients[0]
                confirmedAllergenIngredients[allergen] = confirmedIngredient

                // And remove them from the lists of potential matches
                allergenToIngredients.values.forEach { possibleIngredients -> possibleIngredients.remove(confirmedIngredient) }
            }
        }

        l.debug("Confirmed allergens and ingredients: $confirmedAllergenIngredients")

        // Return the result as a comma-separated list (the map is sorted on allergen name)
        return confirmedAllergenIngredients.values.joinToString(",")
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
