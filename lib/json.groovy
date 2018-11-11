import groovy.json.JsonOutput
import groovy.json.JsonSlurperClassic

def read_json(filePath) {
  readJson = read(File(file: filePath))
  data = new JsonSlurperClassic().parseText(readJson)
  return data
}

def write_json(data, filePath) {
  json = JsonOutPut.toJson(data)
  writeFile(file: filePath, text: JsonOutPut.prettyPrint(json))
}

return this