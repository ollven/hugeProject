import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildFeatures.PullRequests
import jetbrains.buildServer.configs.kotlin.buildFeatures.pullRequests
import jetbrains.buildServer.configs.kotlin.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2021.2"

project {

    vcsRoot(HttpsGithubComGradleGradleGit)

    buildType(A)

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

            for (j in 1..numConfigurationsPerProject) {
                buildType {
                    id("subProj_bt_$i" + "_$j")
                    name = "bt $i $j"
                    
    vcs {
        root(HttpsGithubComGradleGradleGit)
    }

    features {
        pullRequests {
            vcsRootExtId = "${HttpsGithubComGradleGradleGit.id}"
            provider = github {
                authType = token {
                    token = "credentialsJSON:710498e1-c878-4b58-b5f1-cee3a258db6f"
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

object A : BuildType({
    name = "a"

    vcs {
        root(HttpsGithubComGradleGradleGit)
    }

    features {
        pullRequests {
            vcsRootExtId = "${HttpsGithubComGradleGradleGit.id}"
            provider = github {
                authType = token {
                    token = "credentialsJSON:710498e1-c878-4b58-b5f1-cee3a258db6f"
                }
                filterAuthorRole = PullRequests.GitHubRoleFilter.MEMBER
            }
        }
    }
})

object HttpsGithubComGradleGradleGit : GitVcsRoot({
    name = "https://github.com/gradle/gradle.git"
    url = "https://github.com/gradle/gradle.git"
    branch = "refs/heads/master"
})




