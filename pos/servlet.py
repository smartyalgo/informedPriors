from flask import (
    Flask,
    request)

import spacy

resp = Flask(__name__, template_folder=".")

@resp.route('/parse', methods=['POST'])
def parse():
    data = request.json["body"]
    print("data", data)

    document = nlp(data)
    #
    output = []
    for text in document.sents:
        relations = extract_relations(text)
        try:
            print("Doc:", text.text)
        except:
            print()
        for r1, r2, r3 in relations:
            print()
            print('{:<10} | {} | {}'.format(r1.text,
                                            r3,
                                            r2.lemma_))
            output.append((r1.text,r3,r2.lemma_))
    print(output)
    return str(output)


def extract_relations(doc):
    # merge entities and noun chunks into one token
    # TODO: Why removing this stops the method from functioning
    spans = list(doc.ents) + list(doc.noun_chunks)
    for span in spans:
        span.merge()

    relations = []
    # print(list(spans))
    # for token in filter(lambda w: str(w) == "S. flavida", doc):
    # Extract subject, verb, object
    for token in doc:
        # Token is subject.
        # Head is verb
        # Head -> right is subject
        if token.dep_ == 'nsubj':
            if not token.head.right_edge.is_punct and token.head.right_edge.is_alpha :
                relations.append((token.head.right_edge, token.head, token))
    return relations


# If we're running in stand alone mode, run the application
if __name__ == '__main__':
    print("Start app")
    nlp = spacy.load('en_core_web_sm')
    resp.run(debug=True)
