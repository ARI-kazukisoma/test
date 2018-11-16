
def notifySlack(channelTag, errorCode, templateFile, binding=[],isException=false) {
  def slack = load("lib/slack.groovy")
  def template = load("lib/template.groovy")
  def ERROR = load("constant/error.groovy").getAll()

  def errorMessage = ERROR[errorCode]
  binding.put('__error_code', errorCode)
  binding.put('__error_message', errorMessage)
  ERROR = null
  def message = template.toString(templateFile, binding)

  slack.notifyMessage(channelTag, message)

  if (isException) {
    customError(errorCode, errorMessage)
  }
}

def customError(errorCode, message=null) {

  if (message) {
    error("${errorCode}：${message}")
  }
  
  def ERROR = load("constant/error.groovy").getAll()
  error("${errorCode}：${ERROR[errorCode]}")
  
}
return this