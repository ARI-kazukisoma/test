import groovy.json.JsonOutput
import groovy.json.JsonSlurperClassic

Map read(filePath) {
  readJson = readFile(file: filePath)
  data = new JsonSlurperClassic().parseText(readJson)
  return data
}

void write(data, filePath) {
  json = JsonOutput.toJson(data)
  writeFile(file: filePath, text: JsonOutput.prettyPrint(json))
}

Boolean delete(filePath) {
  if (fileExists(filePath)) {
    def f = new File(filePath) 
    f.delete()
    return true
  }
  return false
}

void createTmpConfFile(data, targetEnv, fileName) {
  json.write(common, "${env.TMP_ENV_CONFIGURATION_PATH}/${targetEnv}/${fileName}")
}

void createAllEnvConfFile(targetEnv) {
  // 構成ファイルをtmpから正式なディレクトリにコピーする
  dir(env.TMP_ENV_CONFIGURATION_PATH) {
    sh "cp -pr ${targetEnv} ${env.ENV_CONFIGURATION_PATH}/"
  }
}

void createEnvConfFile(targetEnv, fileName) {
  dir(env.TMP_ENV_CONFIGURATION_PATH) {
    sh "cp -p ${targetEnv}/${fileName} ${env.ENV_CONFIGURATION_PATH}/${targetEnv}/"
  }

}
return this