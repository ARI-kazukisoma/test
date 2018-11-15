String toString(fileName, binding) {
  sh "sleep 10"
  sh "touch test.txt"
  def f = new File("text.txt")
  def template_dir = "./template"
  def f = new File("${template_dir}/${fileName}")
  def engine = new groovy.text.SimpleTemplateEngine()
  def template = engine.createTemplate(f).make(binding)
  return template.toString()
}

return this