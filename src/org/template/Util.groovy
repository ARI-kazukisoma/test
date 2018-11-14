package org.template


String getString(template_name, binding) {
  def text = libraryResource("org/template/${template_name}")
  // def f = new File(filePath)
  def engine = new groovy.text.SimpleTemplateEngine()
  def templateClass = engine.createTemplate(text).make(binding)
  // templateClass.toString()
  // return template.toString()
  return "aaaa"
}

return this