package org.template


class Template implements Serializable {

  def steps

  Template(steps) {
    this.steps = steps
  }

  String toString(template_name, binding) {
    def text = this.steps.libraryResource("org/template/${template_name}")
    // def f = new File(filePath)
    def engine = new groovy.text.SimpleTemplateEngine()
    def template = engine.createTemplate(text).make(binding)
    return template.toString()
  }
}