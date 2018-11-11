def read_json(filePath) {
  def readJson = read(File(file: filePath))
  return new JsonSlurperClassic().parseText(readJson)
}

def write_json(data, filePath) {
  def json = JsonOutPut.toJson(data)
  writeFile(file: filePath, text: JsonOutPut.prettyPrint(json))
}

return this