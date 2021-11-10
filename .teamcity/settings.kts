import jetbrains.buildServer.configs.kotlin.v2019_2.*


version = "2021.2"

project {
    val numProjects = Integer.parseInt(DslContext.getParameter("numProjects", "20"))
    val numConfigurationsPerProject = Integer.parseInt(DslContext.getParameter("numConfigurationsPerProject", "200"))

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
