package com.ruddell.resumeserver.database


import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.springframework.lang.NonNull
import javax.persistence.*

@Entity
@Table(name="work_experience")
class WorkExperience {
    @Id @GeneratedValue(strategy= GenerationType.AUTO) var id: Int = 0
    @NonNull val position:String = ""
    val companyName:String? = null
    val dates:String? = null
    @Column(columnDefinition = "BLOB") val description:String? = null
}

@Entity
@Table(name="education")
class Education {
    @Id @GeneratedValue(strategy= GenerationType.AUTO) var id: Int = 0
    val schoolName:String? = null
    val degreeName:String? = null
    val graduationDate:String? = null
    val gpa:Float? = null
    val website:String? = null
}

@Entity
@Table(name="skills")
class Skill {
    @Id @GeneratedValue(strategy= GenerationType.AUTO) var id: Int = 0
    @NonNull var categoryName:String = ""
    @Column(columnDefinition = "BLOB") var itemNames:String? = null

    companion object {
        fun fromJson(json:JsonObject) : Skill {
            val categoryName = json.getAsJsonPrimitive("categoryName").asString
            val itemNames = json.getAsJsonArray("itemNames")

            val skill = Skill()
            skill.categoryName = categoryName ?: ""
            skill.itemNames = itemNames.toString()
            return skill
        }
    }

    fun toJson() : JsonObject {
        val json = JsonObject()

        val items = JsonParser().parse(itemNames ?: "[]")

        json.addProperty("categoryName", categoryName)
        json.add("itemNames", items)

        return json
    }
}