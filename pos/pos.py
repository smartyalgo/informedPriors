from pprint import pprint
import spacy

def main(model='en_core_web_sm'):
    print("Start app")
    nlp = spacy.load(model)
    print("Loaded model '%s'" % model)

    document = open("raw").read()
    document = nlp(document)

    for text in document.sents:
        relations = extract_relations(text)
        for r1, r2, r3 in relations:
            print('{:<10}\t{}\t{}'.format(r1.text, r3, r2.lemma_))

def extract_relations(doc):
    # merge entities and noun chunks into one token
    # TODO: Why removing this stops the method from functioning
    spans = list(doc.ents) + list(doc.noun_chunks)
    for span in spans:
        span.merge()

    relations = []
    # for token in filter(lambda w: str(w) == "S. flavida", doc):
    # Extract subject, verb, object
    for token in doc:
        if token.dep_ == 'nsubj':
            # print("is nsubj", token)
            # print(token.head)
            # print(token.head.right_edge)
            relations.append((token.head.right_edge, token.head, token))
    return relations

if __name__ == "__main__":
    main()