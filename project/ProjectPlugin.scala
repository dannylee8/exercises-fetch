import de.heikoseeberger.sbtheader.HeaderPlugin.autoImport._
import de.heikoseeberger.sbtheader.License._
import sbt.Keys._
import sbt._
import sbtorgpolicies.OrgPoliciesPlugin.autoImport._
import sbtorgpolicies._
import sbtorgpolicies.model._

object ProjectPlugin extends AutoPlugin {

  override def trigger: PluginTrigger = allRequirements

  override def requires: Plugins = plugins.JvmPlugin && OrgPoliciesPlugin

  object autoImport {

    lazy val V = new {
      val scala212: String            = "2.12.10"
      val cats: String                = "2.0.0"
      val shapeless: String           = "2.3.3"
      val scalatest: String           = "3.0.8"
      val scalacheck: String          = "1.14.2"
      val scalacheckShapeless: String = "1.2.3"
      val fetch: String               = "1.2.1"
    }
  }

  import autoImport._

  override def projectSettings: Seq[Def.Setting[_]] =
    Seq(
      description := "Scala Exercises: The path to enlightenment",
      startYear := Option(2016),
      orgGithubSetting := GitHubSettings(
        organization = "scala-exercises",
        project = name.value,
        organizationName = "Scala Exercises",
        groupId = "org.scala-exercises",
        organizationHomePage = url("https://www.scala-exercises.org"),
        organizationEmail = "hello@47deg.com"
      ),
      orgLicenseSetting := ApacheLicense,
      scalaVersion := V.scala212,
      scalaOrganization := "org.scala-lang",
      scalacOptions := scalacCommonOptions ++ Seq("-Ypartial-unification"),
      resolvers ++= Seq(
        Resolver.mavenLocal,
        Resolver.sonatypeRepo("snapshots"),
        Resolver.sonatypeRepo("releases")
      ),
      scalacOptions := sbtorgpolicies.model.scalacCommonOptions,
      headerLicense := Some(Custom(s"""| scala-exercises - ${name.value}
                                       | Copyright (C) 2015-2019 47 Degrees, LLC. <http://www.47deg.com>
                                       |
                                       |""".stripMargin))
    )
}
