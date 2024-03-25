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
program = []
variables = {}
functions = {}

# On parcourt chaque ligne du fichier source
while lines < source.length
  code = source[lines].strip
  # On ajoute la ligne courante à la table des lignes
  program.push(source[lines])
  lines += 1
end

# on vérifie si la première ligne correspond à "10 % Bug Free"
if program[0].strip != "10% Bug Free"
  puts "Erreur de syntaxe quelque part entre la première et la dernière ligne"
  exit
end

# on vérifie si la dernière ligne correspond à ":q!"
if program[-1].strip != ":q!"
  puts "Erreur de syntaxe quelque part entre la première et la dernière ligne"
  exit
end

# on vérifie si chaque ligne finit par "----" sauf la première et la dernière ou les lignes vides
program.each do |line|
  if line.strip != "" && line.strip != "10% Bug Free" && line.strip != ":q!" && line.strip[-4..-1] != "----"
    puts "Erreur de syntaxe quelque part à la fin d'une ligne"
    exit
  end
end

puts "Filepath: #{filepath}"
puts "Lines: #{lines}"
puts "Line Table: #{program}"


filename = args[1]
