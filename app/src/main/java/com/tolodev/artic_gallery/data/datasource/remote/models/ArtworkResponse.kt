package com.tolodev.artic_gallery.data.datasource.remote.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ArtworkResponse(
    @Json(name = "config")
    val config: Config,
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "info")
    val info: Info,
    @Json(name = "pagination")
    val pagination: Pagination
)

@JsonClass(generateAdapter = true)
data class Config(
    @Json(name = "iiif_url")
    val iiifUrl: String,
    @Json(name = "website_url")
    val websiteUrl: String
)

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "alt_artist_ids")
    val altArtistIds: List<Any>,
    @Json(name = "alt_classification_ids")
    val altClassificationIds: List<String>,
    @Json(name = "alt_image_ids")
    val altImageIds: List<String>,
    @Json(name = "alt_material_ids")
    val altMaterialIds: List<String>,
    @Json(name = "alt_style_ids")
    val altStyleIds: List<Any>,
    @Json(name = "alt_subject_ids")
    val altSubjectIds: List<String>,
    @Json(name = "alt_technique_ids")
    val altTechniqueIds: List<Any>,
    @Json(name = "alt_titles")
    val altTitles: Any?,
    @Json(name = "api_link")
    val apiLink: String,
    @Json(name = "api_model")
    val apiModel: String,
    @Json(name = "artist_display")
    val artistDisplay: String,
    @Json(name = "artist_id")
    val artistId: Int?,
    @Json(name = "artist_ids")
    val artistIds: List<Int>,
    @Json(name = "artist_title")
    val artistTitle: String?,
    @Json(name = "artist_titles")
    val artistTitles: List<String>,
    @Json(name = "artwork_type_id")
    val artworkTypeId: Int,
    @Json(name = "artwork_type_title")
    val artworkTypeTitle: String,
    @Json(name = "boost_rank")
    val boostRank: Any?,
    @Json(name = "catalogue_display")
    val catalogueDisplay: String?,
    @Json(name = "category_ids")
    val categoryIds: List<String>,
    @Json(name = "category_titles")
    val categoryTitles: List<String>,
    @Json(name = "classification_id")
    val classificationId: String?,
    @Json(name = "classification_ids")
    val classificationIds: List<String>,
    @Json(name = "classification_title")
    val classificationTitle: String?,
    @Json(name = "classification_titles")
    val classificationTitles: List<String>,
    @Json(name = "color")
    val color: Color?,
    @Json(name = "colorfulness")
    val colorfulness: Double?,
    @Json(name = "copyright_notice")
    val copyrightNotice: String?,
    @Json(name = "credit_line")
    val creditLine: String,
    @Json(name = "date_display")
    val dateDisplay: String,
    @Json(name = "date_end")
    val dateEnd: Int,
    @Json(name = "date_qualifier_id")
    val dateQualifierId: Int?,
    @Json(name = "date_qualifier_title")
    val dateQualifierTitle: String,
    @Json(name = "date_start")
    val dateStart: Int,
    @Json(name = "department_id")
    val departmentId: String,
    @Json(name = "department_title")
    val departmentTitle: String,
    @Json(name = "description")
    val description: String?,
    @Json(name = "dimensions")
    val dimensions: String?,
    @Json(name = "dimensions_detail")
    val dimensionsDetail: List<DimensionsDetail>,
    @Json(name = "document_ids")
    val documentIds: List<Any>,
    @Json(name = "edition")
    val edition: Any?,
    @Json(name = "exhibition_history")
    val exhibitionHistory: String?,
    @Json(name = "fiscal_year")
    val fiscalYear: Int?,
    @Json(name = "fiscal_year_deaccession")
    val fiscalYearDeaccession: Any?,
    @Json(name = "gallery_id")
    val galleryId: Int?,
    @Json(name = "gallery_title")
    val galleryTitle: String?,
    @Json(name = "has_advanced_imaging")
    val hasAdvancedImaging: Boolean,
    @Json(name = "has_educational_resources")
    val hasEducationalResources: Boolean,
    @Json(name = "has_multimedia_resources")
    val hasMultimediaResources: Boolean,
    @Json(name = "has_not_been_viewed_much")
    val hasNotBeenViewedMuch: Boolean,
    @Json(name = "id")
    val id: Long,
    @Json(name = "image_id")
    val imageId: String?,
    @Json(name = "inscriptions")
    val inscriptions: String?,
    @Json(name = "internal_department_id")
    val internalDepartmentId: Int,
    @Json(name = "is_boosted")
    val isBoosted: Boolean,
    @Json(name = "is_on_view")
    val isOnView: Boolean,
    @Json(name = "is_public_domain")
    val isPublicDomain: Boolean,
    @Json(name = "is_zoomable")
    val isZoomable: Boolean,
    @Json(name = "latitude")
    val latitude: Any?,
    @Json(name = "latlon")
    val latlon: Any?,
    @Json(name = "longitude")
    val longitude: Any?,
    @Json(name = "main_reference_number")
    val mainReferenceNumber: String,
    @Json(name = "material_id")
    val materialId: String?,
    @Json(name = "material_ids")
    val materialIds: List<String>,
    @Json(name = "material_titles")
    val materialTitles: List<String>,
    @Json(name = "max_zoom_window_size")
    val maxZoomWindowSize: Int,
    @Json(name = "medium_display")
    val mediumDisplay: String,
    @Json(name = "nomisma_id")
    val nomismaId: Any?,
    @Json(name = "on_loan_display")
    val onLoanDisplay: Any?,
    @Json(name = "place_of_origin")
    val placeOfOrigin: String?,
    @Json(name = "provenance_text")
    val provenanceText: String?,
    @Json(name = "publication_history")
    val publicationHistory: String?,
    @Json(name = "publishing_verification_level")
    val publishingVerificationLevel: String,
    @Json(name = "section_ids")
    val sectionIds: List<Any>,
    @Json(name = "section_titles")
    val sectionTitles: List<Any>,
    @Json(name = "short_description")
    val shortDescription: String?,
    @Json(name = "site_ids")
    val siteIds: List<Any>,
    @Json(name = "sound_ids")
    val soundIds: List<Any>,
    @Json(name = "source_updated_at")
    val sourceUpdatedAt: String,
    @Json(name = "style_id")
    val styleId: String?,
    @Json(name = "style_ids")
    val styleIds: List<String>,
    @Json(name = "style_title")
    val styleTitle: String?,
    @Json(name = "style_titles")
    val styleTitles: List<String>,
    @Json(name = "subject_id")
    val subjectId: String?,
    @Json(name = "subject_ids")
    val subjectIds: List<String>,
    @Json(name = "subject_titles")
    val subjectTitles: List<String>,
    @Json(name = "suggest_autocomplete_all")
    val suggestAutocompleteAll: List<SuggestAutocompleteAll>,
    @Json(name = "suggest_autocomplete_boosted")
    val suggestAutocompleteBoosted: String?,
    @Json(name = "technique_id")
    val techniqueId: String?,
    @Json(name = "technique_ids")
    val techniqueIds: List<String>,
    @Json(name = "technique_titles")
    val techniqueTitles: List<String>,
    @Json(name = "term_titles")
    val termTitles: List<String>,
    @Json(name = "text_ids")
    val textIds: List<Any>,
    @Json(name = "theme_titles")
    val themeTitles: List<String>,
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail?,
    @Json(name = "timestamp")
    val timestamp: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "video_ids")
    val videoIds: List<Any>
)

@JsonClass(generateAdapter = true)
data class Info(
    @Json(name = "license_links")
    val licenseLinks: List<String>,
    @Json(name = "license_text")
    val licenseText: String,
    @Json(name = "version")
    val version: String
)

@JsonClass(generateAdapter = true)
data class Pagination(
    @Json(name = "current_page")
    val currentPage: Int,
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "next_url")
    val nextUrl: String,
    @Json(name = "offset")
    val offset: Int,
    @Json(name = "total")
    val total: Int,
    @Json(name = "total_pages")
    val totalPages: Int
)

@JsonClass(generateAdapter = true)
data class Color(
    @Json(name = "h")
    val h: Int,
    @Json(name = "l")
    val l: Int,
    @Json(name = "percentage")
    val percentage: Double,
    @Json(name = "population")
    val population: Int,
    @Json(name = "s")
    val s: Int
)

@JsonClass(generateAdapter = true)
data class DimensionsDetail(
    @Json(name = "clarification")
    val clarification: String?,
    @Json(name = "depth")
    val depth: Int?,
    @Json(name = "diameter")
    val diameter: Any?,
    @Json(name = "height")
    val height: Int,
    @Json(name = "width")
    val width: Int
)

@JsonClass(generateAdapter = true)
data class SuggestAutocompleteAll(
    @Json(name = "contexts")
    val contexts: Contexts,
    @Json(name = "input")
    val input: List<String>,
    @Json(name = "weight")
    val weight: Int?
)

@JsonClass(generateAdapter = true)
data class Thumbnail(
    @Json(name = "alt_text")
    val altText: String,
    @Json(name = "height")
    val height: Int,
    @Json(name = "lqip")
    val lqip: String,
    @Json(name = "width")
    val width: Int
)

@JsonClass(generateAdapter = true)
data class Contexts(
    @Json(name = "groupings")
    val groupings: List<String>
)