import jetbrains.buildServer.configs.kotlin.v2019_2.*


version = "2021.2"

project {
    params {
        password("sec", "credentialsJSON:22315edf-7a6e-41f0-905f-7799fb08e9cd")
        param("a", "a")
        param("cd", "c")
        param("b", "b")
    }
    buildType(subProj_bt_2000_2000)
    val numProjects = Integer.parseInt(DslContext.getParameter("numProjects"))
    val numConfigurationsPerProject = Integer.parseInt(DslContext.getParameter("numConfigurationsPerProject"))

    for (i in 0..numProjects) {
        subProject {
            id("subProj_$i")
            name = "subProj $i"

            for (j in 0..numConfigurationsPerProject) {
                buildType {
                    id("subProj_bt_$i" + "_$j")
                    name = "bt $i $j"
                }
            }
        }
    }

}

object subProj_bt_2000_2000 : BuildType({
    name = "trivial_loong"


})
