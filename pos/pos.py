from pprint import pprint
import spacy

nlp = spacy.load("en")

filename = "raw"

document = open(filename).read()
document = nlp(document)

# pprint(document)
# pprint(list(document.sents))

# pprint(list(document.ents))

sentence = document[3]

print(document.sents)

# for sent in document.sents:
#     for word in sent.word:
#         print(list(word.children))
#     # print(ent , " " , ent.label_)