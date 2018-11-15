/*
時間を扱う関数群
TIMEZONEの設定は定数で定義されています。
*/

import java.text.SimpleDateFormat

Long stringToUnixtime(dateString, format="yyyy/MM/dd HH:mm:ss") {
  def CONSTS = load("../${JOB_NAME}/constant/main.groovy").getAll()
  def sdf = new SimpleDateFormat(format)
  sdf.setTimeZone(TimeZone.getTimeZone(CONSTS.TIMEZONE))
  return sdf.parse(dateString).time / 1000
}

Date now(format="yyyy/MM/dd HH:mm:ss") {
  def CONSTS = load("../${JOB_NAME}/constant/main.groovy").getAll()
  def date = new Date()
  def sdf = new SimpleDateFormat(format)
  sdf.setTimeZone(TimeZone.getTimeZone(CONSTS.TIMEZONE))
  return sdf.format(date)

}
Date stringToDatetime(dateString, format="yyyy/MM/dd HH:mm:ss") {
  def CONSTS = load("../${JOB_NAME}/constant/main.groovy").getAll()
  def sdf = new SimpleDateFormat(format)
  sdf.setTimeZone(TimeZone.getTimeZone(CONSTS.TIMEZONE))
  return sdf.parse(dateString).format(format)
}

return this