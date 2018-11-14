package org.template


String getString(template_name, binding) {
  def text = libraryResource("org/template/${template_name}")
  // def f = new File(filePath)
  def engine = new groovy.text.SimpleTemplateEngine()
  def template = engine.createTemplate(text).make(binding)
  template.toString()
  // return template.toString()
  return "aaaa"
}

return this