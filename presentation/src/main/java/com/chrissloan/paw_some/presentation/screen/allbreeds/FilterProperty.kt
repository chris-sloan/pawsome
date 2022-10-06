package com.chrissloan.paw_some.presentation.screen.allbreeds

sealed class FilterProperty(
    open val type: FilterPropertyType = FilterPropertyType.Numerical,
    open val label: String
) {
    object Adaptability : FilterProperty(label = "Adaptability")
    object AffectionLevel : FilterProperty(label = "Affection Level")
    object ChildFriendly : FilterProperty(label = "Child Friendly")
    object DogFriendly : FilterProperty(label = "Dog Friendly")
    object EnergyLevel : FilterProperty(label = "Energy Level")
    object Experimental : FilterProperty(label = "Experimental")
    object Grooming : FilterProperty(label = "Grooming")
    object Hairless : FilterProperty(label = "Hairless")
    object HealthIssues : FilterProperty(label = "Health Issues")
    object Hypoallergenic : FilterProperty(label = "Hypoallergenic")
    object Indoor : FilterProperty(label = "Indoor")
    object Intelligence : FilterProperty(label = "Intelligence")
    object Natural : FilterProperty(label = "Natural")
    object SheddingLevel : FilterProperty(label = "Shedding Level")
    object ShortLegs : FilterProperty(label = "Short Legs")
    object SocialNeeds : FilterProperty(label = "Social Needs")
    object StrangerFriendly : FilterProperty(label = "Stranger Friendly")
    object SuppressedTail : FilterProperty(label = "Suppressed Tail")
    object Rare : FilterProperty(label = "Rare")
    object Rex : FilterProperty(label = "Rex")
    object Vocalisation : FilterProperty(label = "Vocalisation")

    sealed class FilterPropertyType {
        object Numerical : FilterPropertyType()
        //String,
        //Date etc
    }

    companion object {
        fun allProperties() = listOf(
            Adaptability, AffectionLevel, ChildFriendly, DogFriendly,
            EnergyLevel, Experimental, Grooming, Hairless, HealthIssues,
            Hypoallergenic, Indoor, Intelligence, Natural, SheddingLevel,
            ShortLegs, SocialNeeds, StrangerFriendly, SuppressedTail, Rare,
            Rex, Vocalisation
        )
    }
}


