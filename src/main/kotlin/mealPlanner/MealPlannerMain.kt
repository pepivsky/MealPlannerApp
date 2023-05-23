package mealPlanner

import java.util.*

enum class MealType {
    BREAKFAST, LUNCH, DINNER, NOTHING
}

enum class ACTION {
    ADD, SHOW, EXIT
}

data class Meal(val name: String, val category: MealType, val ingredients: List<String>)

class MealPrinter {
    companion object {
        fun printMealInfo(meal: Meal) {
            println("Category: ${meal.category.name.lowercase()}")
            println("Name: ${meal.name}")
            println("Ingredients: ")
            println(meal.ingredients.joinToString("\n"))
            //println("The meal has been added!")
        }
    }
}

object mealList {
    val meals = mutableListOf<Meal>()
}

fun main() {
    // write your code here

    do {
        val action = getAction()
        when (action) {
            ACTION.ADD.name -> {
                println("Which meal do you want to add (breakfast, lunch, dinner)?")
                do {
                    val category = getCategory()
                    if (category == MealType.NOTHING) {
                        continue
                    }
                    //println("Input the meal's name:")
                    val name = getMealName()
                    val ingredients = getIngredients()

                    /*println(ingredients)
                    println(ingredients.size)*/

                    val meal = Meal(name, category, ingredients)
                    mealList.meals.add(meal)
                    println("The meal has been added!")
                } while (category == MealType.NOTHING)

            }

            ACTION.SHOW.name -> {
                if (mealList.meals.isEmpty()) {
                    println("No meals saved. Add a meal first.")
                    continue
                }
                mealList.meals.forEach { meal ->
                    println()
                    MealPrinter.printMealInfo(meal)
                }
            }

            ACTION.EXIT.name -> {
                break
            }
        }
    } while (action != ACTION.EXIT.name)

    println("Bye!")


}

fun validFormat(input: String): Boolean {
    val regex = "[0123456789@#$%^&*\\s]+".toRegex()
    return if (input.contains(regex) || input.isEmpty()) {
        println("Wrong format. Use letters only!")
        false
    } else {
        true
    }
}

/*
fun validFormatIngredients(input: String): Boolean {
    val regex = "[0123456789@#$%^&*,]+".toRegex()
    //val regex = """^[a-zA-Z]+(?:,\s*[a-zA-Z ]+)*$""".toRegex()
    val regex2 = ",\\s*,".toRegex()
    return if (!input.matches(regex) || input.isEmpty() || input.contains(regex2)) {
        println("Wrong format. Use letters only!")
        false
    } else {
        true
    }
}
*/

fun isInvalidMealFormat(meal: String): Boolean {
    val ingredients = meal.split(",\\s*".toRegex())
    ingredients.forEach {
        if (it.contains("[0123456789@#$%^&*,]+".toRegex()) || it.isBlank()) {
            println("Wrong format. Use letters only!")
            return true
        }
    }
    return false
}


fun getAction(): String {
    println("What would you like to do (add, show, exit)?")
    return readln().toUpperCase()
}

fun getIngredients(): List<String> {
    println("Input the ingredients:")
    return requestFieldIngredients().split(",\\s*".toRegex())
}

fun getMealName(): String {
    println("Input the meal's name:")
    /*var name = ""
    do {
        name = readln()
    } while (!validFormat(name)) // si el formato no es valido pide nuevamente el nombre
    return name*/
    return requestField()
}

fun requestField(): String {
    var field = ""
    do {
        field = readln()
    } while (!validFormat(field)) // si el formato no es valido pide nuevamente el nombre
    return field
}

fun requestFieldIngredients(): String {
    var field = ""
    do {
        field = readln()
    } while (isInvalidMealFormat(field)) // si el formato no es valido pide nuevamente el nombre
    return field
}

fun getCategory(): MealType {
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
            println("Wrong meal category! Choose from: breakfast, lunch, dinner.")
            MealType.NOTHING
        }
    }
}