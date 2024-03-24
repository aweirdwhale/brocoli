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


# On initialise les variables qui vont nous permettre de stocker les informations


puts "Filepath: #{filepath}"
puts "Lines: #{lines}"
puts "Line Table: #{code}"


filename = args[1]
