package com.asalazar.egyptmuseum.data.discovery

import com.asalazar.egyptmuseum.domain.discovery.model.Article
import com.asalazar.egyptmuseum.domain.discovery.model.Category
import com.asalazar.egyptmuseum.domain.discovery.model.CategoryType
import com.asalazar.egyptmuseum.domain.discovery.model.MediaContent

class MockEgyptRepository : EgyptRepository {

    private val baseUrl =
        "https://raw.githubusercontent.com/asalazar-prog/EgyptMuseum/assets/images"
    private val nationalGeographicUrl = "https://content-historia.nationalgeographic.com.es/medio"

    private val lifeArticles = listOf(
        Article(
            id = 1,
            title = "Las abejas en el antiguo Egipto: consideradas las lágrimas de un dios y claves en el comercio",
            description = "Alrededor de su producción surgió una floreciente industria que surtía de este valioso producto a los templos para sus ofrendas, a los médicos para preparar sus recetas y a las élites para endulzar panes y pasteles.",
            coverImageUrl = "$nationalGeographicUrl/2026/03/13/recoleccion-de-miel-en-la-tumba-del-visir-rekhmire-facsimil-obra-de-nina-de-garis-davis-met-nueva-york_2d6932f5_260313090335_1280x990.webp",
            coverDescriptionImage = "Pintura mural egipcia que muestra a dos personas recolectando y procesando panales de miel en recipientes de cerámica.",
            media = MediaContent(
                videoUrl = "https://www.ejemplo.com/video_1.mp4",
                audioUrl = "https://www.ejemplo.com/audio_1.mp3",
                imageCollection = listOf(
                    "https://www.ejemplo.com/imagen_1.jpg",
                    "https://www.ejemplo.com/imagen_2.jpg"
                )
            )
        ),
        Article(
            id = 2,
            title = "La curiosa ceremonia egipcia de la apertura de la boca: el difunto vuelve a la vida",
            description = "Tras fallecer, y antes de ser enterrado en su tumba, la momia del difunto debía ser sometida a una compleja ceremonia para ayudarle a recuperar sus sentidos mediante la magia y, así, poder disfrutar de una vida completa en el más allá.",
            coverImageUrl = "$nationalGeographicUrl/2026/01/19/libro-de-los-muertos-de-hunefer-en-el-que-su-momia-atada-por-un-sacerdote-de-anubis-recibe-el-ritual-de-la-apertura-de-la-boca-practicado-por-un-sacerdote-funerario-y-sus-acolitos_e9c02277_4848519c_260119143715_1280x1048.webp",
            coverDescriptionImage = "Fragmento del Libro de los Muertos de Hunefer representando la ceremonia de apertura de la boca frente a una pirámide blanca.",
            media = MediaContent(
                videoUrl = "https://www.ejemplo.com/video_1.mp4",
                audioUrl = "https://www.ejemplo.com/audio_1.mp3",
                imageCollection = listOf(
                    "https://www.ejemplo.com/imagen_1.jpg",
                    "https://www.ejemplo.com/imagen_2.jpg"
                )
            )
        ),
        Article(
            id = 3,
            title = "Kohl: el maquillaje de los egipcios con propiedades médicas",
            description = "Este cosmético cotidiano tenía funciones protectores y rituales cuya complejidad ha sido confirmada por estudios científicos recientes",
            coverImageUrl = "$nationalGeographicUrl/2026/01/13/el-ojo-de-nefertiti_cac1a1dd_260113100755_1280x1928.webp",
            coverDescriptionImage = "Primer plano de perfil de un busto egipcio resaltando el delineado detallado de los ojos con kohl negro y rasgos finamente esculpidos.",
            media = MediaContent(
                videoUrl = "https://www.ejemplo.com/video_1.mp4",
                audioUrl = "https://www.ejemplo.com/audio_1.mp3",
                imageCollection = listOf(
                    "https://www.ejemplo.com/imagen_1.jpg",
                    "https://www.ejemplo.com/imagen_2.jpg"
                )
            )
        ),
        Article(
            id = 4,
            title = "La higiene personal, una actividad de gran importancia entre los antiguos egipcios",
            description = "En el antiguo Egipto, la higiene iba unida a la salud y a la belleza, y también al buen aspecto. Algunos papiros médicos de diversos períodos ofrecían recetas para oler bien así como para mantener la piel hidratada.",
            coverImageUrl = "$nationalGeographicUrl/2025/12/09/unas-sirvientas-aplican-unguentos-y-perfumes-a-su-senora-dibujo-de-charles-k-wilkinson-basado-en-la-pintura-de-una-tumba-tebana-de-la-dinastia-xviii-met-nueva-york_a4132fce_251209095627_550x589.webp",
            coverDescriptionImage = "Fresco que muestra a una sirvienta aplicando aceites o ungüentos a una mujer noble sentada, destacando la importancia del cuidado personal",
            media = MediaContent(
                videoUrl = "https://www.ejemplo.com/video_1.mp4",
                audioUrl = "https://www.ejemplo.com/audio_1.mp3",
                imageCollection = listOf(
                    "https://www.ejemplo.com/imagen_1.jpg",
                    "https://www.ejemplo.com/imagen_2.jpg"
                )
            )
        ),
        Article(
            id = 5,
            title = "El Wepet Renpet, la espectacular fiesta de Año Nuevo egipcia",
            description = "Los antiguos egipcios ya conmemoraban el Año Nuevo de manera muy similar a la nuestra: intercambiaban regalos, celebraban espléndidos banquetes y llevaban a cabo procesiones en las que exponían a las estatuas de las divinidades a la luz solar para su renovación.",
            coverImageUrl = "$nationalGeographicUrl/2025/12/16/escena-de-unas-invitadas-a-un-banquete-con-conos-aromaticos-sobre-sus-pelucas-tumba-de-nebamon-dinastia-xviii-museo-britanico_1f493444_251216093843_1280x853.webp",
            coverDescriptionImage = "Escena de un banquete egipcio con mujeres sentadas portando conos de incienso aromático sobre sus pelucas, celebrando el festival Wepet Renpet.",
            media = MediaContent(
                videoUrl = "https://www.ejemplo.com/video_1.mp4",
                audioUrl = "https://www.ejemplo.com/audio_1.mp3",
                imageCollection = listOf(
                    "https://www.ejemplo.com/imagen_1.jpg",
                    "https://www.ejemplo.com/imagen_2.jpg"
                )
            )
        ),
    )

    val architectureArticles = listOf(
        Article(
            id = 1,
            title = "Un radar satelital revela una estructura secreta de 2.600 años bajo una ciudad sagrada en Egipto",
            description = "Una tecnología que combina imágenes desde el espacio y análisis del subsuelo permite descubrir lo que la arqueología tradicional no podía.",
            coverImageUrl = "$nationalGeographicUrl/2026/03/31/delta-del-nilo_abc365a2_260331131258_1280x960.webp",
            coverDescriptionImage = "Paisaje del río Nilo fluyendo frente a dunas de arena dorada y un oasis de palmeras verdes bajo un cielo pálido.",
            media = MediaContent(
                videoUrl = "https://www.ejemplo.com/video_1.mp4",
                audioUrl = "https://www.ejemplo.com/audio_1.mp3",
                imageCollection = listOf(
                    "https://www.ejemplo.com/imagen_1.jpg",
                    "https://www.ejemplo.com/imagen_2.jpg"
                )
            )
        ),
        Article(
            id = 2,
            title = "¿Y si los orígenes de la Gran Pirámide de Giza fueran mucho más antiguos de lo que creíamos? Esto es lo que propone un polémico estudio",
            description = "Un ingeniero italiano afirma que la Gran Pirámide de Keops puede tener más de 20.000 años de antigüedad, remontándose a la Edad de Piedra.",
            coverImageUrl = "$nationalGeographicUrl/2026/01/29/gran-piramide_84646c9c_96318936_260129122457_1280x853.webp",
            coverDescriptionImage = "Primer plano de la Gran Pirámide de Giza con bloques de piedra texturizados y restos de un templo de entrada en la base bajo un cielo azul con nubes.",
            media = MediaContent(
                videoUrl = "https://www.ejemplo.com/video_1.mp4",
                audioUrl = "https://www.ejemplo.com/audio_1.mp3",
                imageCollection = listOf(
                    "https://www.ejemplo.com/imagen_1.jpg",
                    "https://www.ejemplo.com/imagen_2.jpg"
                )
            )
        ),
        Article(
            id = 3,
            title = "Hallazgo revolucionario en Egipto: desentierran el templo solar de un faraón de la V dinastía",
            description = "No son muchos los templos solares que se han encontrado, de ahí su importancia. El complejo situado en Abusir data de hace unos 4.400 años.",
            coverImageUrl = "$nationalGeographicUrl/2025/12/17/templo-solar-nyuserra1_1305251e_251217122039_1280x947.webp",
            coverDescriptionImage = "Entrada de piedra maciza y desgastada de una estructura arqueológica, flanqueada por reglas de medición rojas y blancas en el suelo.",
            media = MediaContent(
                videoUrl = "https://www.ejemplo.com/video_1.mp4",
                audioUrl = "https://www.ejemplo.com/audio_1.mp3",
                imageCollection = listOf(
                    "https://www.ejemplo.com/imagen_1.jpg",
                    "https://www.ejemplo.com/imagen_2.jpg"
                )
            )
        ),
        Article(
            id = 4,
            title = "Misterio en Egipto: detectan dos anomalías en la pirámide de Micerino que podrían ocultar una entrada secreta",
            description = "Los científicos han descubierto varias cavidades llenas de aire en la cara oriental de esta pirámide de Giza, lo que ha reavivado la hipótesis de que existía una segunda entrada al monumento.",
            coverImageUrl = "$nationalGeographicUrl/2025/11/13/piramide-micerino_974e36b0_251113105435_1280x853.webp",
            coverDescriptionImage = "Vista panorámica de las tres Grandes Pirámides de Giza alineadas sobre la arena del desierto bajo un cielo azul intenso.",
            media = MediaContent(
                videoUrl = "https://www.ejemplo.com/video_1.mp4",
                audioUrl = "https://www.ejemplo.com/audio_1.mp3",
                imageCollection = listOf(
                    "https://www.ejemplo.com/imagen_1.jpg",
                    "https://www.ejemplo.com/imagen_2.jpg"
                )
            )
        ),
        Article(
            id = 5,
            title = "Un nuevo estudio propone una revolucionaria teoría sobre el modo en que los antiguos egipcios habrían construido la Gran Pirámide de Giza",
            description = "Una revolucionaria y novedosa teoría sugiere que los bloques de granito que se utilizaron para la construcción de la Gran Pirámide de Keops en Giza se levantaron mediante unos sistemas de poleas internas que se impulsaban con contrapesos deslizantes.",
            coverImageUrl = "$nationalGeographicUrl/2025/10/26/la-gran-piramide-de-keops-es-la-unica-de-las-siete-maravillas-del-mundo-que-queda-en-pie_c287f0fe_96318936_251026114511_1280x853.webp",
            coverDescriptionImage = "Vista de la Gran Pirámide con restos de un templo en primer plano bajo un cielo nublado.",
            media = MediaContent(
                videoUrl = "https://www.ejemplo.com/video_1.mp4",
                audioUrl = "https://www.ejemplo.com/audio_1.mp3",
                imageCollection = listOf(
                    "https://www.ejemplo.com/imagen_1.jpg",
                    "https://www.ejemplo.com/imagen_2.jpg"
                )
            )
        ),
    )

    val artArticle = listOf<Article>(
        Article(
            id = 1,
            title = "'Tipp-Ex' del antiguo Egipto: el secreto detrás de un error artístico de hace 3.300 años",
            description = "Un descubrimiento en el Libro de los Muertos ha revelado que algunos artesanos egipcios ya utilizaban una técnica parecida al corrector líquido moderno.",
            coverImageUrl = "$nationalGeographicUrl/2026/03/13/papiro_80d0316f_260313131816_1280x853.webp",
            coverDescriptionImage = "Fragmento de papiro egipcio que muestra dos escenas: a la izquierda, un hombre atendiendo a un chacal negro (Anubis) y, a la derecha, una mujer adorando frente a una mesa de ofrendas. Incluye inscripciones jeroglíficas en la base y figuras vestidas con lino blanco plisado.",
            media = MediaContent(
                videoUrl = "https://www.ejemplo.com/video_1.mp4",
                audioUrl = "https://www.ejemplo.com/audio_1.mp3",
                imageCollection = listOf(
                    "https://www.ejemplo.com/imagen_1.jpg",
                    "https://www.ejemplo.com/imagen_2.jpg"
                )
            )
        ),
        Article(
            id = 2,
            title = "Una exposición en Nueva York muestra por primera vez un extraño tesoro funerario egipcio de 2.000 años",
            description = "La muestra trae de vuelta un tesoro funerario que llevaba miles de años 'desaparecido'",
            coverImageUrl = "$nationalGeographicUrl/2026/03/10/llustrated-book-of-the-dead_cf619178_260310121927_1280x1598.webp",
            coverDescriptionImage = "Esta imagen muestra un dibujo lineal egipcio antiguo con detalles en pan de oro sobre papiro fragmentado. A la izquierda, hay dos figuras de perfil, una con cabeza de halcón y otra con un tocado de disco y pluma, ambas con detalles de oro en sus joyas y coronas. A la derecha, se muestra una mesa cargada con ofrendas que incluyen panes, aves y vasijas.",
            media = MediaContent(
                videoUrl = "https://www.ejemplo.com/video_1.mp4",
                audioUrl = "https://www.ejemplo.com/audio_1.mp3",
                imageCollection = listOf(
                    "https://www.ejemplo.com/imagen_1.jpg",
                    "https://www.ejemplo.com/imagen_2.jpg"
                )
            )
        ),
        Article(
            id = 3,
            title = "Los Buchis, los otros toros sagrados del antiguo Egipto",
            description = "En el antiguo Egipto, algunos animales eran considerados encarnaciones de una divinidad. Es el caso de los toros Apis, adorados en Saqqara. Pero otros toros también fueron objeto de culto.",
            coverImageUrl = "$nationalGeographicUrl/2026/02/23/la-procesion-del-sagrado-toro-apis-frederick-arthur-bridgman-1870_00000000_1bb2b825_260223145717_1280x805.webp",
            coverDescriptionImage = "Pintura al óleo que representa una procesión egipcia antigua en un templo. Incluye a un buey negro adornado con flores, sacerdotes cargando una barca ceremonial dorada, músicos, bailarines y figuras reales caminando entre grandes columnas policromadas.",
            media = MediaContent(
                videoUrl = "https://www.ejemplo.com/video_1.mp4",
                audioUrl = "https://www.ejemplo.com/audio_1.mp3",
                imageCollection = listOf(
                    "https://www.ejemplo.com/imagen_1.jpg",
                    "https://www.ejemplo.com/imagen_2.jpg"
                )
            )
        ),
        Article(
            id = 4,
            title = "Un relieve de hace 5.000 años en el Sinaí se convierte en una de las primeras imágenes de la conquista egipcia",
            description = "Los arqueólogos encuentran un panel de arte rupestre en Wadi Khamila que representa uno de los primeros actos visuales de dominación política de la historia humana. Habla de sometimiento y alude al control egipcio de recursos.",
            coverImageUrl = "$nationalGeographicUrl/2026/02/04/mural-sinai_f31279f1_260204120908_1280x719.webp",
            coverDescriptionImage = "Una fotografía que muestra petroglifos grabados en la cara de un acantilado de piedra arenisca de color tostado con luz brillante y sombreado. Los grabados en negro, delineados a mano o superpuestos digitalmente, incluyen representaciones de una figura humana de pie con los brazos levantados; dos símbolos similares a barcos, uno de los cuales tiene un gran círculo texturizado y un mástil; una figura humana de pie y otra arrodillada cerca de un animal de cuatro patas; y otras formas abstractas. También se ven algunas figuras tenues grabadas que no están delineadas, y una pequeña etiqueta rectangular blanca se encuentra cerca del centro.",
            media = MediaContent(
                videoUrl = "https://www.ejemplo.com/video_1.mp4",
                audioUrl = "https://www.ejemplo.com/audio_1.mp3",
                imageCollection = listOf(
                    "https://www.ejemplo.com/imagen_1.jpg",
                    "https://www.ejemplo.com/imagen_2.jpg"
                )
            )
        ),
        Article(
            id = 5,
            title = "El magnífico pectoral de oro y lapislázuli de la reina Kama descubierto en Leontópolis",
            description = "En 1915, el arqueólogo escocés Campbell Cowan Edgar descubrió un hipogeo con dos cámaras decoradas con imágenes religiosas, en una de las cuales halló un sarcófago de granito rojo que contenía la momia de una reina de la dinastía XXIII y algunos vestigios del que, sin duda, fue su lujoso ajuar funerario.",
            coverImageUrl = "$nationalGeographicUrl/2026/01/28/anverso-del-pectoral-de-kama-ii-descubierto-en-el-yacimiento-de-leontopolismuseo-egipcio-de-el-cairo-egipto_a09b7d6f_260128100234_550x592.webp",
            coverDescriptionImage = "Pectoral de oro y lapislázuli del Antiguo Egipto que muestra un escarabajo azul central sobre una flor de loto, flanqueado por las diosas Isis y Neftis en actitud de adoración bajo tres discos solares dorados.",
            media = MediaContent(
                videoUrl = "https://www.ejemplo.com/video_1.mp4",
                audioUrl = "https://www.ejemplo.com/audio_1.mp3",
                imageCollection = listOf(
                    "https://www.ejemplo.com/imagen_1.jpg",
                    "https://www.ejemplo.com/imagen_2.jpg"
                )
            )
        ),
    )

    private val categories = listOf(
        Category(
            id = CategoryType.LIFE,
            title = "Vida Cotidiana",
            subtitle = "Comercio, familia y tradiciones a orillas del Nilo.",
            imageUrl = "$baseUrl/egypt_life_category.webp",
            articles = lifeArticles
        ),
        Category(
            id = CategoryType.ARCHITECTURE,
            title = "Arquitectura",
            subtitle = "Monumentos eternos y la ingeniería de los dioses.",
            imageUrl = "$baseUrl/egypt_architecture_category.webp",
            articles = architectureArticles
        ),
        Category(
            id = CategoryType.ART,
            title = "Arte",
            subtitle = "Esculturas, frescos y la belleza de lo sagrado.",
            imageUrl = "$baseUrl/egypt_art_category.webp",
            articles = artArticle
        )
    )

    override fun getCategories(): List<Category> = categories

    override fun getCategoryById(id: CategoryType): Category? {
        return categories.find { it.id == id }
    }

    override fun getArticle(
        id: Int,
        categoryType: CategoryType
    ): Article? = getCategoryById(categoryType)?.articles?.find { it.id == id }

}
