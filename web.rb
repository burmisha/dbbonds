require 'sinatra'
require 'pg'

# conn = PGConn.open(:dbname => 'd98ldghnhunfae')
# res = conn.exec('SELECT SUM(value) FROM numbers')
# answer = res.getvalue(0, 0)

get '/' do
  "<h1>Hello, world! Specially for team 6.</h1> Sum in DB is " # + answer.to_s + "."
end
