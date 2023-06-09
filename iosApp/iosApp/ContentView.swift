import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

	var body: some View {
        List {
            VStack (alignment: .leading) {
                Text("Saved String")
                TextField("new String", text: $viewModel.savedString)
                Button(
                    action: {
                        viewModel.saveString()
                    },
                    label: {
                        Text("save")
                    }
                )
            }
            ForEach(viewModel.githubRepositories, id: \.self) { item in
                GithubRepositoryItemView(data: item)
            }
        }
	}
}

struct GithubRepositoryItemView: View {
    private(set) var data: GithubRepositoryModel
    
    var body: some View {
        VStack (alignment: .leading) {
            Text(data.fullName)
            if data.description_ != nil { Text(data.description_!) }
        }
    }
}

extension ContentView {
    class ViewModel: ObservableObject {
        
        @Published var savedString = ""
        @Published var githubRepositories: [GithubRepositoryModel] = []
        let repository = Repository()
        
        init() {
            savedString = repository.getSavedString()
            repository.getGithubRepositories() { result, _ in
                DispatchQueue.main.async {
                    if result != nil {
                        self.githubRepositories = result!.items
                    }
                }
            }
        }
        
        func getSavedString() -> String {
            return repository.getSavedString()
        }
        
        func saveString() {
            repository.saveString(string: savedString)
        }
    }
}
