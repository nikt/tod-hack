repositories.remote << "http://repo1.maven.org/maven2"

MOCKITO = 'org.mockito:mockito-all:jar:1.9.0'
HAMCREST = 'org.hamcrest:hamcrest-all:jar:1.1'
LOG4J = 'log4j:log4j:jar:1.2.17'
LOMBOK = 'org.projectlombok:lombok:jar:0.11.0'
#LWJGL = 'org.lwjgl.lwjgl:lwjgl:jar:2.8.3'

LWJGL = struct(
  'CORE' => 'org.lwjgl.lwjgl:lwjgl:jar:2.8.4',
  'UTIL' => 'org.lwjgl.lwjgl:lwjgl_util:jar:2.8.4'
)

JAG2D = 'garjust:jag2d:jar:1.1.0-SNAPSHOT'

DEPENDENCIES = LOG4J, LOMBOK, LWJGL
TEST_DEPENDENCIES = MOCKITO, HAMCREST