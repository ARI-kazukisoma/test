String to_string(filePath, binding) {
  def f = new File(filePath)
  def engine = new groovy.text.SimpleTemplateEngine()
  def template = engine.createTemplate(f).make(binding)
  return template.toString()
}

return this