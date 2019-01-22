package com.dzcx.log.plugin

import com.android.build.gradle.BaseExtension
import com.dzcx.log.plugin.utils.DataHelper
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.dzcx.log.plugin.utils.Log

class InjectPluginImpl implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println ":applied LazierTracker"
        project.extensions.create('codelessConfig', InjectPluginParams)
        registerTransform(project)
        initDir(project);
        project.afterEvaluate {
            Log.setQuiet(project.codelessConfig.keepQuiet);
            Log.setShowHelp(project.codelessConfig.showHelp);
            Log.logHelp();
            if (project.codelessConfig.watchTimeConsume) {
                Log.info "watchTimeConsume enabled"
                project.gradle.addListener(new TimeListener())
            } else {
                Log.info "watchTimeConsume disabled"
            }
        }
    }

    def static registerTransform(Project project) {
//        def isApp = project.plugins.hasPlugin("com.android.application")
        BaseExtension android = project.extensions.getByType(BaseExtension)
        InjectTransform transform = new InjectTransform(project)
        android.registerTransform(transform)
    }

    static void initDir(Project project) {
        File pluginTmpDir = new File(project.buildDir, 'LazierTracker')
        if (!pluginTmpDir.exists()) {
            pluginTmpDir.mkdir()
        }
        DataHelper.ext.pluginTmpDir = pluginTmpDir
    }

}
