package Stage1

import java.util.*

enum class MealType {
    BREAKFAST, LUNCH, DINNER, NOTHING
}

data class Meal(val name: String, val category: MealType, val ingredients: List<String>)

class MealPrinter {
    companion object {
        fun printMealInfo(meal: Meal) {
            println("Category: ${meal.category.name.lowercase()}")
            println("Name: ${meal.name}")
            println("Ingredients: ")
            println(meal.ingredients.joinToString("\n"))
            println("The meal has been added!")
        }
    }
}

fun main() {
    // write your code here
    val category = getCategory()
    val name = getMealName()
    val ingredients = getIngredients()
    val meal = Meal(name, category, ingredients)
    MealPrinter.printMealInfo(meal)

}

fun getIngredients(): List<String> {
    println("Input the ingredients:")
    return readln().split(",\\s*".toRegex())
}

fun getMealName(): String {
    println("Input the meal's name:")
    return readln()
}

fun getCategory(): MealType {
    println("Which meal do you want to add (breakfast, lunch, dinner)?")
    return when (readln().uppercase(Locale.getDefault())) {
        MealType.BREAKFAST.name -> {
            MealType.BREAKFAST
        }
        MealType.LUNCH.name -> {
            MealType.LUNCH
        }
        MealType.DINNER.name -> {
            MealType.DINNER
        }
        else -> {
            MealType.NOTHING
        }
    }
}