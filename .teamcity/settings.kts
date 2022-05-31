import jetbrains.buildServer.configs.kotlin.v2019_2.*

import jetbrains.buildServer.configs.kotlin.v2019_2.ui.*
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot
import jetbrains.buildServer.configs.kotlin.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.buildFeatures.parallelTests
import jetbrains.buildServer.configs.kotlin.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.buildSteps.gradle


create(DslContext.projectId, GitVcsRoot({
    id("HttpsGithubComGradleGradleGit")
    name = "https://github.com/gradle/gradle.git"
    url = "https://github.com/gradle/gradle.git"
    branch = "refs/heads/master"
}))



version = "2022.04"

project {
    params {
        password("sec", "credentialsJSON:22315edf-7a6e-41f0-905f-7799fb08e9cd")
        param("a", "a")
        param("cd", "cjss")
        param("b", "b")
    }

    //val numProjects = Integer.parseInt(DslContext.getParameter("numProjects"))
    val numProjects=1
    val numConfigurationsPerProject=500
    //val numConfigurationsPerProject = Integer.parseInt(DslContext.getParameter("numConfigurationsPerProject"))

    for (i in 1..numProjects) {
        subProject {
            id("subProj_$i")
            name = "subProj $i"
                vcs {
        root(HttpsGithubComGradleGradleGit)
      }


            for (j in 0..numConfigurationsPerProject) {
                buildType {
                    id("subProj_bt_$i" + "_$j")
                    name = "bt $i $j"
                    features {
    
                        pullRequests {
                        vcsRootExtId = "${HttpsGithubComGradleGradleGit.id}"
                        provider = github {
                            authType = token {
                            token = password("sec", "credentialsJSON:22315edf-7a6e-41f0-905f-7799fb08e9ca")
                                               }
                       filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
                                            }
                                       }
                                }
                                                         }
            }
        }
    }

}
