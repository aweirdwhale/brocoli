# On récupère les arguments passés en ligne de commande
args = ARGV

# On vérifie qu'un argument a bien été passé
if args.empty? || args[0] != "--s"
  puts "Usage: ruby interpreter.rb --s <source>"
  exit
end

if args[1].nil?
  puts "Usage: You must specify a source file."
  exit
end

# On vérifie que le fichier source existe
if !File.exist?(args[1])
  puts "File not found."
  exit
end

# On lit le fichier source ligne par ligne et on stoque le contenu dans une liste
source = File.readlines(args[1])

# On initialise les variables
filepath = args[1]
lines = 0
lineTable = []
variables = {}
functions = {}

# On parcourt chaque ligne du fichier source
while lines < source.length
  # On ajoute la ligne courante à la table des lignes
  lineTable.push(source[lines])
  lines += 1
end

puts "Filepath: #{filepath}"
puts "Lines: #{lines}"
puts "Line Table: #{lineTable}"


filename = args[1]
