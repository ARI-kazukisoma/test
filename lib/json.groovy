import groovy.json.JsonOutput
import groovy.json.JsonSlurperClassic

def read_json(filePath) {
  readJson = read(File(file: filePath))
  data = new JsonSlurperClassic().parseText(readJson)
  return data
}

def write_json(data, filePath) {
  json = JsonOutput.toJson(data)
  writeFile(file: filePath, text: JsonOutput.prettyPrint(json))
}

return this