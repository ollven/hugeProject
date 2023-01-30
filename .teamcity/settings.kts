import jetbrains.buildServer.configs.kotlin.v2019_2.*

import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

version = "2021.2"

project {

    vcsRoot(HttpsGithubComGradleGradleGit)


    val numProjects=1
    val numConfigurationsPerProject=800


    for (i in 0..numProjects) {
        subProject {
            id("subProj_$i")
            name = "subProj $i"

            for (j in 0..numConfigurationsPerProject) {
                buildType {
                    id("subProj_bt_$i" + "_$j")
                    name = "bt $i $j"
                    
    vcs {
        root(HttpsGithubComGradleGradleGit)
    }

                }
            }
        }
    }
}


object HttpsGithubComGradleGradleGit : GitVcsRoot({
    name = "https://github.com/ChubatovaTiger/ChubatovaGradleTestsBackup"
    url = "https://github.com/ChubatovaTiger/ChubatovaGradleTestsBackup"
    branch = "refs/heads/master"
})




