String toString(template_name, binding) {
  def text = libraryResource("org/template/${template_name}")
  // def f = new File(filePath)
  def engine = new groovy.text.SimpleTemplateEngine()
  def template = engine.createTemplate(text).make(binding)
  return template.toString()
}

return this